package ua.yaskal.controller;

/**
 * This interface used for getting to frontend
 *
 * @author Nazar Yaskal
 */
public interface JspPath {
    String REG_FORM = "guest/regForm";
    String LOGIN_FORM = "guest/loginForm";
    String ERROR404 = "errors/404error";

    String USER_HOME = "user/userHome";
    String USER_DEPOSIT_OPEN = "user/depositOpen";
    String USER_CREDIT_OPEN = "user/newCreditRequest";
    String USER_ALL_ACCOUNTS = "user/allAccounts";
    String USER_REPLENISH_ACCOUNT = "user/replenishAccount";
    String USER_MAKE_TRANSACTION = "user/makeTransaction";
    String USER_MAKE_PAYMENT = "user/makePayment";
    String USER_ALL_PAYMENTS = "user/allPayments";
    String USER_CREDIT_PAGE = "user/userCreditPage";
    String USER_DEPOSIT_PAGE = "user/userDepositPage";


    String ADMIN_HOME = "admin/adminHome";
    String ADMIN_ALL_USERS = "admin/allUsers";
    String ADMIN_ALL_DEPOSITS = "admin/allDeposits";
    String ADMIN_ALL_CREDITS = "admin/allCredits";
    String ADMIN_ALL_CREDIT_REQUESTS = "admin/allCreditRequests";
    String ADMIN_CREDIT_REQUEST = "admin/creditRequestPage";
    String ADMIN_USER_PAGE = "admin/userPage";
    String ADMIN_CREDIT_PAGE = "admin/adminCreditPage";
    String ADMIN_DEPOSIT_PAGE = "admin/adminDepositPage";


}
