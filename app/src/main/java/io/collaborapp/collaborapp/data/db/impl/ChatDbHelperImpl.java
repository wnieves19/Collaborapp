package io.collaborapp.collaborapp.data.db.impl;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.collaborapp.collaborapp.data.db.ChatDbHelper;
import io.collaborapp.collaborapp.data.model.ChatDbUpdate;
import io.collaborapp.collaborapp.data.model.ChatEntity;
import io.collaborapp.collaborapp.data.model.MessageEntity;
import io.collaborapp.collaborapp.firebase.RxFirebase;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static io.reactivex.BackpressureStrategy.BUFFER;

@Singleton
public class ChatDbHelperImpl implements ChatDbHelper {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private List<ChatEntity> mChatList;

    @Inject
    public ChatDbHelperImpl(FirebaseAuth firebaseAuth, FirebaseDatabase firebaseDatabase) {
        mFirebaseDatabase = firebaseDatabase;
        mAuth = firebaseAuth;
    }

    @Override
    public Flowable<ChatEntity> fetchChatList() {
        return Flowable.create(e -> mFirebaseDatabase.getReference().child("user-chats").child(mAuth.getCurrentUser().getUid()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        ChatEntity chat = dataSnapshot.getValue(ChatEntity.class);
                        getChatMessages(chat.getChatId()).subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(messageList -> {
                                    chat.setMessageList(messageList);
                                    e.onNext(chat);
                                    e.onComplete();
                                });
                        //TODO Check if chat exists in list
                        addChatToList(chat);
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        ChatEntity chatEntity = getChat(dataSnapshot.getKey());
                        chatEntity.getChatFlowableEmitter().onNext(new ChatDbUpdate(chatEntity.getChatId(), ChatDbUpdate.NEW_MESSAGE));
                        //TODO:Receive messages - Send meesages to a method that returns an observable to which the chatpresenter method is subscribed
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
                }), BUFFER
        );


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
        mChatList.add(chat);
    }

    @Override
    public Observable<Object> createChat(List<String> userIds, @Nullable String groupName) {
        userIds.add(mAuth.getCurrentUser().getUid());

        ChatEntity existingChat = getExistingChat(userIds);
        if (existingChat != null) return Observable.create(emitter -> emitter.onNext(existingChat));

        String chatKey = mFirebaseDatabase.getReference()
                .child("user-chats")
                .child(mAuth.getCurrentUser().getUid())
                .push().getKey();

        ChatEntity newChat = new ChatEntity(chatKey);
        newChat.setMembers(userIds);

        for (String userId : userIds) {
            if (!mAuth.getCurrentUser().getUid().equals(userId)) {
                createChatToUser(newChat, userId);
            } else {
                return RxFirebase.getObservable(createChatToUser(newChat, userId), newChat);
            }
        }

        return null;
    }

    private ChatEntity getExistingChat(List<String> userIds) {
        for (ChatEntity chat : getChatList()) {
            int counter = 0;
            for (String userId : chat.getMembers()) {
                for (String id : userIds) {
                    if (id.equals(userId)) {
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
    private Task<Void> createChatToUser(ChatEntity newChat, String userId) {
        return mFirebaseDatabase.getReference()
                .child("user-chats")
                .child(userId)
                .child(newChat.getChatId())
                .setValue(newChat);
    }

    @Override
    public Observable<Boolean> deleteChat(String[] chatId) {
        //TODO: Change chat property to depict delition, return boolean
        return null;
    }

    @Override
    public void sendMessage(String chatId, MessageEntity message) {

    }

    @Override
    public void deleteMessage(String messageId) {

    }
}
