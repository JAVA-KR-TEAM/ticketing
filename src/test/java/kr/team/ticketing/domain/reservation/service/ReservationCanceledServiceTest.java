package kr.team.ticketing.domain.reservation.service;

import static kr.team.ticketing.common.Fixtures.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kr.team.ticketing.domain.reservation.Reservation;
import kr.team.ticketing.domain.reservation.ReservationRepository;

@ExtendWith(MockitoExtension.class)
class ReservationCanceledServiceTest {
	@Mock private ReservationRepository reservationRepository;

	private ReservationCanceledService reservationCanceledService;

	@BeforeEach
	void setUp() {
		reservationCanceledService = new ReservationCanceledServiceImpl(reservationRepository);
	}

	@DisplayName("예매 취소")
	@Test
	void canceledReservation() {
		Reservation reservation = aReservation().build();

		when(reservationRepository.findById(reservation.getId())).thenReturn(Optional.of(reservation));

		reservationCanceledService.cancelReservation(reservation.getId());

		assertThat(reservation.getStatus()).isEqualTo(Reservation.ReservationStatus.CANCELED);
		assertThat(reservation.getDeleted()).isEqualTo(1);
	}
}