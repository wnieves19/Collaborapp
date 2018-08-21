package io.collaborapp.collaborapp.data.model;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

import static io.reactivex.BackpressureStrategy.BUFFER;

/**
 * Created by wilfredonieves on 11/9/17.
 */

public class ChatEntity {
    private String chatId;
    private String title = "";
    private List<MessageEntity> messageList;
    private MessageEntity lastMessage;
    private List<String> members;
    private long createdAt;

    private Flowable<ChatDbUpdate> chatObservable;
    private FlowableEmitter<ChatDbUpdate> chatFlowableEmitter;

    public ChatEntity() {
        chatObservable = Flowable.create(e -> this.chatFlowableEmitter = e, BUFFER);
    }

    public void emitChatUpdate(ChatDbUpdate dbUpdate){
        if(chatFlowableEmitter!=null) {
            chatFlowableEmitter.onNext(dbUpdate);
        }
    }

    public Flowable<ChatDbUpdate> getChatObservable() {
        return chatObservable;
    }

    public ChatEntity(String chatId) {
        this.chatId = chatId;
    }

    public String getChatId() {
        return chatId;
    }

    public String getTitle() {
        return title;
    }

    public List<MessageEntity> getMessageList() {
        return messageList;
    }

    public List<String> getMembers() {
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

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

}
