package kr.team.ticketing.domain.reservation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.team.ticketing.domain.BaseEntity;
import kr.team.ticketing.domain.object.generic.money.Money;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationLineItem extends BaseEntity {
	@Column
	private Long productId;
	@JsonIgnore
	@ManyToOne
	private Reservation reservation;
	@Column
	private String description;
	@Column
	private int count;
	@OneToMany(mappedBy = "lineItem", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReservationOption> reserveOptions = new ArrayList<>();

	@Builder
	public ReservationLineItem(Long productId, String description, List<ReservationOption> reserveOptions) {
		this.productId = productId;
		this.description = description;
		this.count = reservationCount();
		addReservationOptions(reserveOptions);
	}

	public void addReservationOption(ReservationOption reservationOption) {
		reservationOption.setLineItem(this);
		this.reserveOptions.add(reservationOption);
	}

	public void addReservationOptions(List<ReservationOption> reserveOptions) {
		reserveOptions.forEach(o -> addReservationOption(o));
	}

	public void setReservation(Reservation reservation) {
		if (this.reservation != null) {
			this.reservation.getLineItems().remove(this);
		}
		this.reservation = reservation;
	}

	public Money calculatePrice() {
		return Money.sum(reserveOptions, ReservationOption::getPrice).times(count);
	}

	private int reservationCount() {
		return reserveOptions.size();
	}
}
