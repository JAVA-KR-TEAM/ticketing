package kr.team.ticketing.domain.reservation.service;

import org.springframework.stereotype.Component;

import kr.team.ticketing.domain.reservation.Reservation;
import kr.team.ticketing.domain.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReservationCanceledServiceImpl implements ReservationCanceledService {
	private final ReservationRepository reservationRepository;

	@Override
	public void cancelReservation(Long reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId)
			.orElseThrow(IllegalArgumentException::new);
		reservation.canceled();
	}
}
