package ua.yaskal.model.exeptions.key.no.such;

public class NoSuchCreditRequestException extends NoSuchException {
    private String messageKey="info.exception.no.such.credit.request";
    @Override
    public String getMessageKey() {
        return messageKey;
    }
}
