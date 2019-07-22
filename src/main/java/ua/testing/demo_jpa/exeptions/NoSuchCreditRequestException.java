package ua.testing.demo_jpa.exeptions;

public class NoSuchCreditRequestException extends RuntimeException {
    public NoSuchCreditRequestException() {
        super();
    }

    public NoSuchCreditRequestException(String message) {
        super(message);
    }
}
