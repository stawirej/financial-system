package domain.exceptions;

public final class EmployeeAlreadyExists extends RuntimeException {

    public EmployeeAlreadyExists(String message) {
        super(message);
    }

}
