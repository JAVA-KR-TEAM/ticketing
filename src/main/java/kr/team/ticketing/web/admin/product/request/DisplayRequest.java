package kr.team.ticketing.web.admin.product.request;

import kr.team.ticketing.domain.product.display.LocationCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class DisplayRequest {
	private Long productId;
	private String openingHours;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime startDisplayDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime endDisplayDate;
	private String place;
	private String placeLot;
	private String placeStreet;
	private String locationCode;
	private String tel;
	private String homePage;
	private String email;

	@Builder
	public DisplayRequest(Long productId, String openingHours, LocalDateTime startDisplayDate,
		LocalDateTime endDisplayDate, String place, String placeLot, String placeStreet, String locationCode,
		String tel, String homePage, String email) {
		this.productId = productId;
		this.openingHours = openingHours;
		this.startDisplayDate = startDisplayDate;
		this.endDisplayDate = endDisplayDate;
		this.place = place;
		this.placeLot = placeLot;
		this.placeStreet = placeStreet;
		this.locationCode = locationCode;
		this.tel = tel;
		this.homePage = homePage;
		this.email = email;
	}
}
