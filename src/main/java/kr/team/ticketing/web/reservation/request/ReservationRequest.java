package kr.team.ticketing.web.reservation.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReservationRequest {
	private String name;
	private String email;
	private String tel;
	private List<ReservationOptionRequest> optionRequests;

	@Builder
	public ReservationRequest(String name, String email, String tel, List<ReservationOptionRequest> optionRequests) {
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.optionRequests = optionRequests;
	}
}
