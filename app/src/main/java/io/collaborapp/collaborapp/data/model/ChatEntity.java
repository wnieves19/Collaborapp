package io.collaborapp.collaborapp.data.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private List<UserEntity> users;
    private long createdAt;

    private Flowable<ChatDbUpdate> chatObservable;

    private FlowableEmitter<ChatDbUpdate> chatFlowableEmitter;

    public ChatEntity() {
        chatObservable = Flowable.create(e -> this.chatFlowableEmitter = e, BUFFER);
    }

    public void emitChatUpdate(ChatDbUpdate dbUpdate) {
        if (chatFlowableEmitter != null) {
            chatFlowableEmitter.onNext(dbUpdate);
        }
    }

    public FlowableEmitter<ChatDbUpdate> getEmitter() {
        return chatFlowableEmitter;
    }

    public Flowable<ChatDbUpdate> getChatObservable() {
        return chatObservable;
    }

    public ChatEntity(String chatId) {
        this.chatId = chatId;
        this.createdAt = System.currentTimeMillis();
    }

    public String getChatId() {
        return chatId;
    }

    public String getTitle() {
        if(!"".equals(title))return title;
        for(UserEntity user : users){
            if(!user.getUserId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                return user.getFirstName()+ " " + user.getLastName();
            }
        }
        return null;
    }

    public List<MessageEntity> getMessageList() {
        return messageList;
    }

    public List<UserEntity> getUserList() { return users; }

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

    public void setMemberList(List<UserEntity> userList) {
        this.users = userList;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("chatId", chatId);
        result.put("title", title);
        result.put("createdAt", createdAt);
        result.put("lastMessage", lastMessage);
        result.put("members", getUserIds());

        return result;
    }

    private List<String> getUserIds() {
        List<String> userIds = new ArrayList<>();

        for (UserEntity user : users) {
            userIds.add(user.getUserId());
        }
        return userIds;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

}
