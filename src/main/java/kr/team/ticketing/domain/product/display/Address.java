package kr.team.ticketing.domain.product.display;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
public class Address {
	@Column
	private String place;
	@Column
	private String placeLot;
	@Column
	private String placeStreet;

	@Builder
	public Address(String place, String placeLot, String placeStreet) {
		this.place = place;
		this.placeLot = placeLot;
		this.placeStreet = placeStreet;
	}
}
