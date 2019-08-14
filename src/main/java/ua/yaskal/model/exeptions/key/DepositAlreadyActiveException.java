package ua.yaskal.model.exeptions.key;



public class DepositAlreadyActiveException extends MessageKeyException {
    private String messageKey="info.exception.deposit.already.active";
    @Override
    public String getMessageKey() {
        return messageKey;
    }
}
