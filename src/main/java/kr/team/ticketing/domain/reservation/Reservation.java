package kr.team.ticketing.domain.reservation;

import kr.team.ticketing.domain.BaseEntity;
import kr.team.ticketing.domain.object.Email;
import kr.team.ticketing.domain.object.generic.money.Money;
import lombok.*;

import org.hibernate.annotations.Where;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Where(clause = "deleted = 0")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseEntity {
	public enum ReservationStatus {PAYMENT_WAITING, RESERVED_COMPLETE, CANCELED}

	@Column
	private Long memberId;
	@Column
	private String name;
	@Embedded
	private Email email;
	@Column
	private String tel;
	@Column
	private LocalDateTime reserveDate;
	@Enumerated(EnumType.STRING)
	@Column
	private ReservationStatus status;
	@Column
	private int deleted = 0;
	@OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReservationLineItem> lineItems = new ArrayList<>();

	@Builder
	public Reservation(Long id, Long memberId, String name, Email email, String tel,
		List<ReservationLineItem> lineItems) {
		this.id = id;
		this.memberId = memberId;
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.reserveDate = LocalDateTime.now();
		addLineItems(lineItems);
	}

	public void register(ReservationValidator reservationValidator) {
		reservationValidator.validate(this);
		paymentWaiting();
	}

	public List<Long> getProductIds() {
		return lineItems.stream().map(ReservationLineItem::getProductId).collect(Collectors.toList());
	}

	public void addLineItem(ReservationLineItem lineItem) {
		lineItem.setReservation(this);
		this.lineItems.add(lineItem);
	}

	public void addLineItems(List<ReservationLineItem> lineItems) {
		lineItems.forEach(i -> addLineItem(i));
	}

	public void delete() {
		this.deleted = 1;
	}

	public void paymentWaiting() {
		this.status = ReservationStatus.PAYMENT_WAITING;
	}

	public void reservedComplete() {
		this.status = ReservationStatus.RESERVED_COMPLETE;
	}

	public void canceled() {
		verifyPayment();
		this.status = ReservationStatus.CANCELED;
	}

	private void verifyPayment() {
		if (this.status == ReservationStatus.PAYMENT_WAITING)
			throw new IllegalStateException("Haven't Payment to Reservation");
	}

	public Money calculateTotalPrice() {
		return Money.sum(lineItems, ReservationLineItem::calculatePrice);
	}
}
