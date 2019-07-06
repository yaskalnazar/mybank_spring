package ua.testing.demo_jpa.exeptions;

public class NoUsersInBdExeption extends RuntimeException {

    public NoUsersInBdExeption() {
        super();
    }

    public NoUsersInBdExeption(String message) {
        super(message);
    }
}
