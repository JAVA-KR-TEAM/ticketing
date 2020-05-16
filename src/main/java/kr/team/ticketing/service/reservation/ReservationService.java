package kr.team.ticketing.service.reservation;

import kr.team.ticketing.domain.member.Member;
import kr.team.ticketing.domain.reservation.Reservation;
import kr.team.ticketing.domain.reservation.service.ReservationCanceledService;
import kr.team.ticketing.domain.reservation.service.ReservationPayedService;
import kr.team.ticketing.domain.reservation.ReservationRepository;
import kr.team.ticketing.domain.reservation.ReservationValidator;
import kr.team.ticketing.web.reservation.request.ReservationRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {
	private final ReservationRepository reservationRepository;
	private final ReservationMaker reservationMaker;
	private final ReservationValidator reservationValidator;
	private final ReservationPayedService reservationPayedService;
	private final ReservationCanceledService reservationCanceledService;

	@Transactional
	public void registerReservation(Member loginMember, ReservationRequest request) {
		Reservation reservation = reservationMaker.makeReservation(loginMember.getId(), request);
		reservation.register(reservationValidator);
		reservationRepository.save(reservation);
	}

	@Transactional
	public void payReservation(Long reservationId) {
		reservationPayedService.payReservation(reservationId);
	}

	@Transactional
	public void canceledReservation(Long reservationId) {
		reservationCanceledService.cancelReservation(reservationId);
	}
}
