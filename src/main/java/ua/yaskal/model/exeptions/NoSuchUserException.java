package ua.yaskal.model.exeptions;


public class NoSuchUserException extends RuntimeException {
    public NoSuchUserException() {
        super();
    }

    public NoSuchUserException(String message) {
        super(message);
    }
}
