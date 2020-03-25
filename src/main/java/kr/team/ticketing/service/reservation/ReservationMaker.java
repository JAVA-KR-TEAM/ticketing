package kr.team.ticketing.service.reservation;

import kr.team.ticketing.domain.member.Member;
import kr.team.ticketing.domain.object.Email;
import kr.team.ticketing.domain.object.generic.money.Money;
import kr.team.ticketing.domain.reservation.Reservation;
import kr.team.ticketing.domain.reservation.ReservationLineItem;
import kr.team.ticketing.domain.reservation.ReservationOption;
import kr.team.ticketing.web.reservation.request.ReservationOptionRequest;
import kr.team.ticketing.web.reservation.request.ReservationRequest;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationMaker {

	public Reservation makeReservation(Long memberId, ReservationRequest request) {
		return Reservation.builder()
			.memberId(memberId)
			.name(request.getName())
			.email(new Email(request.getEmail()))
			.tel(request.getTel())
			.lineItems(createLineItems(request.getOptionRequests()))
			.build();
	}

	private List<ReservationLineItem> createLineItems(List<ReservationOptionRequest> optionRequests) {
		List<ReservationLineItem> lineItems = new ArrayList<>();
		lineItems.add(ReservationLineItem.builder()
			.productId(optionRequests.get(0).getProductId())
			.description(optionRequests.get(0).getDescription())
			.reserveOptions(optionRequests.stream()
				.map(ReservationMaker::convertOption)
				.collect(Collectors.toList()))
			.build());
		return lineItems;
	}

	private static ReservationOption convertOption(ReservationOptionRequest optionRequest) {
		return new ReservationOption(optionRequest.getName(), Money.wons(optionRequest.getPrice()));
	}
}
