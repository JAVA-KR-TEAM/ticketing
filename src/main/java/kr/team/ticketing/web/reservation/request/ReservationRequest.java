package kr.team.ticketing.web.reservation.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {
	private String name;
	private String email;
	private String tel;
	private List<ReservationOptionRequest> optionRequests;
}
