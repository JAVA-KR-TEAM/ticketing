package kr.team.ticketing.web.admin.product.request;

import kr.team.ticketing.domain.product.display.LocationCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class DisplayRequest {
	private Long productId;
	private String openingHours;
	private String startDate;
	private String endDate;
	private String place;
	private String placeLot;
	private String placeStreet;
	private String locationCode;
	private String tel;
	private String homePage;
	private String email;

	@Builder
	public DisplayRequest(Long productId, String openingHours, String startDate, String endDate, String place, String placeLot, String placeStreet, String locationCode, String tel, String homePage, String email) {
		this.productId = productId;
		this.openingHours = openingHours;
		this.startDate = startDate;
		this.endDate = endDate;
		this.place = place;
		this.placeLot = placeLot;
		this.placeStreet = placeStreet;
		this.locationCode = locationCode;
		this.tel = tel;
		this.homePage = homePage;
		this.email = email;
	}
}
