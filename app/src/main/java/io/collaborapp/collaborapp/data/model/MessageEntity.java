package io.collaborapp.collaborapp.data.model;

/**
 * Created by wilfredonieves on 11/3/17.
 */

public class MessageEntity {
    private String messageId;
    private String senderId;
    private String messageText;
    private long timeStamp;
    private String multimediaUrl;
    private String type;

    public String getSender() {
        return senderId;
    }

    public void setSender(String sender) {
        this.senderId = sender;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMultimediaUrl() {
        return multimediaUrl;
    }

    public void setMultimediaUrl(String multimediaUrl) {
        this.multimediaUrl = multimediaUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
