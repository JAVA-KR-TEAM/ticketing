package kr.team.ticketing.web.reservation.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationOptionRequest {
	private Long productId;
	private String description;
	private String name;
	private int price;
}
