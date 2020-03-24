package kr.team.ticketing.domain.reservation.exception;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException() {
    }

    public ReservationNotFoundException(String message) {
        super(message);
    }

    public ReservationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
