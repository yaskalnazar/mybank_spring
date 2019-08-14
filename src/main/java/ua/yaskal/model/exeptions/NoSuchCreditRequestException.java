package ua.yaskal.model.exeptions;

public class NoSuchCreditRequestException extends RuntimeException {
    public NoSuchCreditRequestException() {
        super();
    }

    public NoSuchCreditRequestException(String message) {
        super(message);
    }
}
