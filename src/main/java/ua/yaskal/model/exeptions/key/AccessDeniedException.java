package ua.yaskal.model.exeptions.key;

public class AccessDeniedException extends MessageKeyException {
    private String messageKey="page.message.access.denied";

    @Override
    public String getMessageKey() {
        return messageKey;
    }
}
