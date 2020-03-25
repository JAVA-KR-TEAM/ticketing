package kr.team.ticketing.domain.product.exception;

public class DisplayNotFoundException extends RuntimeException {
    public DisplayNotFoundException() {
    }

    public DisplayNotFoundException(String message) {
        super(message);
    }

    public DisplayNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
