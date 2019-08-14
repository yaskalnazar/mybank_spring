package ua.yaskal.model.exeptions;

/**
 * This exception is thrown while trying register with already registered email
 *
 * @author Nazar Yaskal
 * @see ua.yaskal.model.dao.jdbc.JDBCUserDAO;
 */
public class NonUniqueEmailException extends RuntimeException {
}
