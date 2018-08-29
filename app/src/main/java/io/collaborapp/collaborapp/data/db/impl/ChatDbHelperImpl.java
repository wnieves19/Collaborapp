package io.collaborapp.collaborapp.data.db.impl;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.collaborapp.collaborapp.data.db.ChatDbHelper;
import io.collaborapp.collaborapp.data.model.ChatDbUpdate;
import io.collaborapp.collaborapp.data.model.ChatEntity;
import io.collaborapp.collaborapp.data.model.MessageEntity;
import io.collaborapp.collaborapp.data.model.UserEntity;
import io.collaborapp.collaborapp.firebase.RxFirebase;
import io.collaborapp.collaborapp.utils.NotificationManager;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static io.reactivex.BackpressureStrategy.BUFFER;

@Singleton
public class ChatDbHelperImpl implements ChatDbHelper {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private List<ChatEntity> mChatList;
    private DatabaseReference mChatReference;
    private ChildEventListener eventListener;
    private NotificationManager mNotificationManager;

    @Inject
    public ChatDbHelperImpl(FirebaseAuth firebaseAuth,
                            FirebaseDatabase firebaseDatabase,
                            NotificationManager notificationManager) {
        mFirebaseDatabase = firebaseDatabase;
        mAuth = firebaseAuth;
        mNotificationManager = notificationManager;
        mChatReference = mFirebaseDatabase.getReference().child("user-chats").child(mAuth.getCurrentUser().getUid());
    }

    @Override
    public Flowable<ChatEntity> fetchChatList() {
        removeListener();
        return Flowable.create(e -> mChatReference.addChildEventListener(getChatEventListener(e)), BUFFER);
    }

    public ChildEventListener getChatEventListener(FlowableEmitter<ChatEntity> chatEmitter) {
        eventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ChatEntity chat = dataSnapshot.getValue(ChatEntity.class);
                getChatMessages(chat.getChatId()).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(messageList -> {
                            chat.setMessageList(messageList);
                        });
                getChatMembers(chat.getChatId()).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(usersList -> {
                            chat.setMemberList(usersList);
                            chatEmitter.onNext(chat);
                        });
                addChatToList(chat);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ChatEntity chatEntity = getChat(dataSnapshot.getKey());
                ChatEntity dbChat = dataSnapshot.getValue(ChatEntity.class);
                chatEntity.setLastMessage(dbChat.getLastMessage());
                chatEntity.getMessageList().add(dbChat.getLastMessage());
                emitIncomingMessage(chatEntity, chatEmitter);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        };
        return eventListener;
    }

    private void emitIncomingMessage(ChatEntity chatEntity, FlowableEmitter<ChatEntity> chatEmitter) {
        if (chatEntity.getEmitter() != null && !chatEntity.getEmitter().isCancelled()) {
            chatEntity.emitChatUpdate(new ChatDbUpdate(chatEntity, ChatDbUpdate.NEW_MESSAGE));
        }
        if (!chatEmitter.isCancelled()) {
            chatEmitter.onNext(chatEntity);
        } else {
            mNotificationManager.showChatNotification(chatEntity);
        }
    }

    private void removeListener() {
        if (mChatReference == null || eventListener == null) return;
        mChatReference.removeEventListener(eventListener);
    }

    private Observable<List<MessageEntity>> getChatMessages(String chatId) {
        return Observable.create(e -> mFirebaseDatabase.getReference().child("chat-messages").child(chatId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<MessageEntity> list = new ArrayList<>();
                        for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                            MessageEntity message = messageSnapshot.getValue(MessageEntity.class);
                            list.add(message);
                        }
                        e.onNext(list);
                        e.onComplete();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                })
        );
    }

    private Observable<List<UserEntity>> getChatMembers(String chatId) {
        return Observable.create(e -> mFirebaseDatabase.getReference().child("chat-members").child(chatId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<UserEntity> list = new ArrayList<>();
                        for (DataSnapshot memberSnapshot : dataSnapshot.getChildren()) {
                            UserEntity user = memberSnapshot.getValue(UserEntity.class);
                            list.add(user);
                        }
                        e.onNext(list);
                        e.onComplete();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                })
        );
    }

    @Override
    public List<ChatEntity> getChatList() {
        if (mChatList == null) mChatList = new ArrayList<>();
        return mChatList;
    }

    @Override
    public ChatEntity getChat(String chatId) {
        for (ChatEntity chat : getChatList()) {
            if (chat.getChatId().equals(chatId)) return chat;
        }
        return null;
    }

    private void addChatToList(ChatEntity chat) {
        if (mChatList == null) mChatList = new ArrayList<>();
        if (getChat(chat.getChatId()) == null)
            mChatList.add(chat);
    }

    @Override
    public Observable<Object> createChat(List<UserEntity> users, @Nullable String groupName) {
//        users.add(mAuth.getCurrentUser().getUid());
        ChatEntity existingChat = getExistingChat(users);
        //If existing chat exist return it in Observable
        if (existingChat != null) return Observable.create(emitter -> emitter.onNext(existingChat));
        //Get new chat key
        String chatKey = mFirebaseDatabase.getReference()
                .child("user-chats")
                .child(mAuth.getCurrentUser().getUid())
                .push().getKey();
        //Create new chat instance
        ChatEntity newChat = new ChatEntity(chatKey);
        newChat.setMemberList(users);

        for (UserEntity user : users) {
            if (!mAuth.getCurrentUser().getUid().equals(user.getUserId())) {
                createChatToUser(newChat.toMap(), user.getUserId());
            } else {
                return RxFirebase.getObservable(createChatToUser(newChat.toMap(), user.getUserId()), newChat);
            }
        }

        return null;
    }

    private ChatEntity getExistingChat(List<UserEntity> users) {
        for (ChatEntity chat : getChatList()) {
            int counter = 0;
            for (UserEntity user : chat.getUserList()) {
                for (UserEntity newChatUser : users) {
                    if (newChatUser.getUserId().equals(user.getUserId())) {
                        counter++;
                    }
                }
            }
            if (counter == 2) {
                return chat;
            }
        }
        return null;
    }

    @NonNull
    private Task<Void> createChatToUser(Map<String, Object> newChat, String userId) {
        return mFirebaseDatabase.getReference()
                .child("user-chats")
                .child(userId)
                .child(newChat.get("chatId").toString())
                .setValue(newChat);
    }

    @Override
    public void sendMessage(String chatId, MessageEntity message) {
        String messageKey = mFirebaseDatabase.getReference().child("chat-messages").child(chatId).push().getKey();
        message.setMessageId(messageKey);
        message.setFrom(mAuth.getCurrentUser().getUid());
        mFirebaseDatabase.getReference().child("chat-messages").child(chatId).child(message.getMessageId()).setValue(message);
        Map<String, Object> childUpdates = new HashMap<>();
        for (UserEntity user : getChat(chatId).getUserList()) {
            childUpdates.put("/user-chats/" + user.getUserId() + "/" + chatId + "/lastMessage", message);
        }
        mFirebaseDatabase.getReference().updateChildren(childUpdates);
    }

    @Override
    public Observable<Boolean> deleteChat(String[] chatId) {
        //TODO: Change chat property to depict delition, return boolean
        return null;
    }

    @Override
    public void deleteMessage(String messageId) {

    }
}
