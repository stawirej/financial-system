package domain.exceptions;

public final class NotExistingEmployee extends RuntimeException {

    public NotExistingEmployee(String message) {
        super(message);
    }
}
