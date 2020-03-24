package kr.team.ticketing.service.reservation;

import kr.team.ticketing.domain.reservation.Reservation;
import kr.team.ticketing.web.reservation.request.ReservationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static kr.team.ticketing.common.Fixtures.aReservationRequest;
import static org.assertj.core.api.Assertions.assertThat;

class ReservationMakerTest {
    private ReservationMaker reservationMaker;

    @BeforeEach
    void setUp() {
        reservationMaker = new ReservationMaker();
    }

    @DisplayName("예매 정보 생성")
    @Test
    void makeReservation() {
        ReservationRequest request = aReservationRequest().build();
        Reservation reservation = reservationMaker.makeReservation(1l, request);

        assertThat(reservation).isNotNull();
        assertThat(reservation.getLineItems().size()).isEqualTo(1);
    }
}