package kr.team.ticketing.domain.reservation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.team.ticketing.domain.BaseEntity;
import kr.team.ticketing.domain.object.generic.money.Money;
import kr.team.ticketing.domain.product.detail.ConvertOption;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationOption extends BaseEntity {
	@JsonIgnore
	@ManyToOne
	private ReservationLineItem lineItem;
	@Column
	private String name;
	@Column
	private Money price;

	public ReservationOption(String name, Money price) {
		this.name = name;
		this.price = price;
	}

	public void setLineItem(ReservationLineItem lineItem) {
		if (this.lineItem != null) {
			this.lineItem.getReserveOptions().remove(this);
		}
		this.lineItem = lineItem;
	}

	public ConvertOption convertToOption() {
		return new ConvertOption(name, price);
	}
}
