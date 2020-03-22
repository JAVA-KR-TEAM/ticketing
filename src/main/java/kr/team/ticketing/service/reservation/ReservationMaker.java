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

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationMaker {

    public Reservation makeReservation(Member loginMember, ReservationRequest request) {
        Reservation reservation = Reservation.builder()
                .memberId(loginMember.getId())
                .name(request.getName())
                .email(new Email(request.getEmail()))
                .tel(request.getTel())
                .build();
        reservation.addLineItem(createLineItem(request.getOptionRequests()));
        return reservation;
    }

    private ReservationLineItem createLineItem(List<ReservationOptionRequest> optionRequests) {
        List<ReservationOption> options = optionRequests.stream()
                .map(ReservationMaker::convertOption)
                .collect(Collectors.toList());
        ReservationLineItem lineItem = new ReservationLineItem(optionRequests.get(0).getProductId());
        lineItem.addReservationOptions(options);
        return lineItem;
    }

    private static ReservationOption convertOption(ReservationOptionRequest optionRequest) {
        return new ReservationOption(optionRequest.getName(), Money.wons(optionRequest.getPrice()));
    }
}
