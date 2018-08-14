package io.collaborapp.collaborapp.data.db.impl;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.collaborapp.collaborapp.data.db.ChatDbHelper;
import io.collaborapp.collaborapp.data.model.ChatEntity;
import io.collaborapp.collaborapp.data.model.MessageEntity;
import io.reactivex.Observable;
@Singleton
public class ChatDbHelperImpl implements ChatDbHelper {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private List<ChatEntity> mChatList;

    @Inject
    public ChatDbHelperImpl(FirebaseAuth firebaseAuth, FirebaseDatabase firebaseDatabase) {
        mFirebaseDatabase = firebaseDatabase;
        mAuth = firebaseAuth;

        mFirebaseDatabase.getReference().child("user-chats").child(mAuth.getCurrentUser().getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               ChatEntity chat =  dataSnapshot.getValue(ChatEntity.class);
               chat.setChatId(dataSnapshot.getKey());
               addChatToList(chat);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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
        });
    }

    private void addChatToList(ChatEntity chat) {
        if(mChatList==null)mChatList = new ArrayList<>();
        mChatList.add(chat);
    }

    @Override
    public Observable<ChatEntity> createChat(String[] userId, @Nullable String groupName) {
        return null;
    }

    @Override
    public Observable deleteChat(String[] chatId) {
        return null;
    }

    @Override
    public void sendMessage(String chatId, MessageEntity message) {

    }

    @Override
    public void deleteMessage(String messageId) {

    }
}
