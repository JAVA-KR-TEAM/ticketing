package kr.team.ticketing.domain.reservation.service;

import org.springframework.stereotype.Component;

import kr.team.ticketing.domain.reservation.Reservation;
import kr.team.ticketing.domain.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReservationPayedServiceImpl implements ReservationPayedService {
	private final ReservationRepository reservationRepository;

	@Override
	public void payReservation(Long reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId)
			.orElseThrow(IllegalArgumentException::new);
		reservation.reservedComplete();
	}
}
