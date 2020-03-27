package kr.team.ticketing.domain.product.display;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
	@Column
	@Enumerated(EnumType.STRING)
	private LocationCode locationCode;

	@Builder
	public Address(String place, String placeLot, String placeStreet, String locationCode) {
		this.place = place;
		this.placeLot = placeLot;
		this.placeStreet = placeStreet;
		this.locationCode = LocationCode.valueOf(locationCode);
	}
}
