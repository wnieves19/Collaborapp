package io.collaborapp.collaborapp.data.entities;

/**
 * Created by wilfredonieves on 11/3/17.
 */

public class MessageEntity {
    private UserEntity sender;
    private String messageText;
    private long timeStamp;
    private String multimediaUrl;


    public UserEntity getSender() {
        return sender;
    }

    public void setSender(UserEntity sender) {
        this.sender = sender;
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
}
