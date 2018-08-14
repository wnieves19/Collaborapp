package io.collaborapp.collaborapp.data.model;

import java.util.List;

/**
 * Created by wilfredonieves on 11/9/17.
 */

public class ChatEntity {

    private String chatId;
    public String title;
    private List<MessageEntity> messageList;
    public MessageEntity lastMessage;
    private List<UserEntity> members;
    private long createdAt;

    public String getChatId() {
        return chatId;
    }

    public String getTitle() {
        return title;
    }

    public List<MessageEntity> getMessageList() {
        return messageList;
    }

    public List<UserEntity> getMembers() {
        return members;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public MessageEntity getLastMessage() {
        return lastMessage;
    }

    public void setChatId(String chatId) {

        this.chatId = chatId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessageList(List<MessageEntity> messageList) {
        this.messageList = messageList;
    }

    public void setLastMessage(MessageEntity lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void setMembers(List<UserEntity> members) {
        this.members = members;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
