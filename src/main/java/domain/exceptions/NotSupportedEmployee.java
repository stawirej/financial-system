package domain.exceptions;

public final class NotSupportedEmployee extends RuntimeException {

    public NotSupportedEmployee(String message) {
        super(message);
    }
}
