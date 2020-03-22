package kr.team.ticketing.domain.reservation;

import kr.team.ticketing.domain.DomainTests;
import kr.team.ticketing.domain.object.Email;
import kr.team.ticketing.domain.object.generic.money.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static kr.team.ticketing.domain.reservation.Reservation.ReservationStatus.PAYMENT_WAITING;
import static org.assertj.core.api.Assertions.assertThat;

class ReservationTest extends DomainTests {
    @Autowired
    private ReservationRepository reservationRepository;

    @BeforeEach
    void setup() {
        Reservation reservation = Reservation.builder()
                .memberId(1l)
                .name("김철수")
                .email(new Email("chulsu@naver.com"))
                .tel("010-2331-1233")
                .build();

        ReservationLineItem lineItem = new ReservationLineItem(1l);

        List<ReservationOption> reservationOptions = asList(
                new ReservationOption("성인", Money.wons(17000)),
                new ReservationOption("청소년", Money.wons(15000)),
                new ReservationOption("유아", Money.wons(13000))
        );

        reservation.addLineItem(lineItem);
        lineItem.addReservationOptions(reservationOptions);

        reservationRepository.save(reservation);
        em.clear();
    }

    @DisplayName("예매 내역 삭제")
    @Test
    void findReserve() {
        Reservation dbReservation = reservationRepository.findById(1l).get();

        dbReservation.delete();
        dbReservation.getLineItems().clear();

        assertThat(dbReservation.getLineItems().size()).isEqualTo(0);
        assertThat(dbReservation.getDeleted()).isEqualTo(1);
    }
}