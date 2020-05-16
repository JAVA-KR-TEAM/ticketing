package kr.team.ticketing.domain.reservation.service;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static kr.team.ticketing.common.Fixtures.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import kr.team.ticketing.domain.reservation.Reservation;
import kr.team.ticketing.domain.reservation.ReservationRepository;


@ExtendWith(MockitoExtension.class)
class ReservationPayedServiceTest {
	@Mock private ReservationRepository reservationRepository;

	private ReservationPayedService reservationPayedService;

	@BeforeEach
	void setUp() {
		reservationPayedService = new ReservationPayedServiceImpl(reservationRepository);
	}

	@DisplayName("결제 완료")
	@Test
	void reservedComplete() {
		Reservation reservation = aReservation().build();

		when(reservationRepository.findById(reservation.getId())).thenReturn(Optional.of(reservation));

		reservationPayedService.payReservation(reservation.getId());

		assertThat(reservation.getStatus()).isEqualTo(Reservation.ReservationStatus.RESERVED_COMPLETE);
	}
}