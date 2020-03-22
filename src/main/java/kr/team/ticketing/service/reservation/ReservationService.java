package kr.team.ticketing.service.reservation;

import kr.team.ticketing.domain.member.Member;
import kr.team.ticketing.domain.reservation.Reservation;
import kr.team.ticketing.domain.reservation.ReservationRepository;
import kr.team.ticketing.web.reservation.request.ReservationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMaker reservationMaker;

    public Reservation registerReservation(Member loginMember, ReservationRequest request) {
        Reservation reservation = reservationMaker.makeReservation(loginMember, request);
        return reservationRepository.save(reservation);
    }
}
