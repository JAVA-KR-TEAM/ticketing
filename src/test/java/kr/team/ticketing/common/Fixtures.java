package kr.team.ticketing.common;

import kr.team.ticketing.domain.member.Member;
import kr.team.ticketing.domain.object.Email;
import kr.team.ticketing.domain.object.generic.money.Money;
import kr.team.ticketing.domain.reservation.Reservation;
import kr.team.ticketing.domain.reservation.ReservationLineItem;
import kr.team.ticketing.domain.reservation.ReservationOption;
import kr.team.ticketing.web.reservation.request.ReservationOptionRequest;
import kr.team.ticketing.web.reservation.request.ReservationRequest;

import java.util.List;

import static java.util.Arrays.asList;

public class Fixtures {
    public static ReservationRequest.ReservationRequestBuilder aReservationRequest() {
        return ReservationRequest.builder()
                .name("김철수")
                .email("chulsu@naver.com")
                .tel("010-2314-4123")
                .optionRequests(asList(
                        ReservationOptionRequest.builder().productId(1l).name("ADULT").price(23000).build(),
                        ReservationOptionRequest.builder().productId(1l).name("TEENAGER").price(19000).build(),
                        ReservationOptionRequest.builder().productId(1l).name("CHILDREN").price(17000).build()
                ));
    }

    public static Member.MemberBuilder aMember() {
        return Member.builder()
                .name("rebwon12")
                .email(new Email("rebwon@naver.com"));
    }

    public static Reservation aReservation() {
        Reservation reservation = Reservation.builder()
                .memberId(1l)
                .name("김철수")
                .email(new Email("chulsu@naver.com"))
                .tel("010-2314-1235")
                .build();
        reservation.addLineItem(aReservationLineItem());
        return reservation;
    }

    public static ReservationLineItem aReservationLineItem() {
        ReservationLineItem lineItem = new ReservationLineItem(1l);
        lineItem.addReservationOptions(anReservationOptions());
        return lineItem;
    }

    public static List<ReservationOption> anReservationOptions() {
        return asList(
                new ReservationOption("ADULT", Money.wons(23000)),
                new ReservationOption("TEENAGER", Money.wons(19000)),
                new ReservationOption("CHILDREN", Money.wons(17000))
        );
    }
}
