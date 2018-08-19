package io.collaborapp.collaborapp.data.model;

/**
 * Created by wilfredonieves on 11/3/17.
 */

public class MessageEntity {
    private String messageId;
    private String from;
    private String text;
    private long createdAt;
    private String multimediaUrl;
    private String type;

    public String getSender() {
        return from;
    }

    public void setSender(String sender) {
        this.from = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
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
