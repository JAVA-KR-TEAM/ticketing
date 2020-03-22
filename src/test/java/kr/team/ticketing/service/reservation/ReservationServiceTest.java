package kr.team.ticketing.service.reservation;

import kr.team.ticketing.domain.member.Member;
import kr.team.ticketing.domain.reservation.ReservationRepository;
import kr.team.ticketing.web.reservation.request.ReservationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static kr.team.ticketing.common.Fixtures.aMember;
import static kr.team.ticketing.common.Fixtures.aReservationRequest;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {
    @Mock private ReservationRepository reservationRepository;
    @Mock private ReservationMaker reservationMaker;

    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        reservationService = new ReservationService(reservationRepository, reservationMaker);
    }

    @DisplayName("예매 정보 생성")
    @Test
    void registerReservation() {
        Member member = aMember().build();
        ReservationRequest request = aReservationRequest().build();

        reservationService.registerReservation(member, request);

        verify(reservationMaker).makeReservation(member, request);
    }
}