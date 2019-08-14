package ua.yaskal.model.exeptions;

public class NoUsersInBdExeption extends RuntimeException {

    public NoUsersInBdExeption() {
        super();
    }

    public NoUsersInBdExeption(String message) {
        super(message);
    }
}
