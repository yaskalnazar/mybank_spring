package ua.yaskal.model.exeptions.key.no.such;

public class NoSuchPageException extends NoSuchException {
    private String messageKey="info.exception.no.such.page";

    @Override
    public String getMessageKey() {
        return messageKey;
    }
}
