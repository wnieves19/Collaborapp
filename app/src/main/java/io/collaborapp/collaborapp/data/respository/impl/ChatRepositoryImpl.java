package io.collaborapp.collaborapp.data.respository.impl;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import io.collaborapp.collaborapp.data.entities.ChatEntity;
import io.collaborapp.collaborapp.data.entities.MessageEntity;
import io.collaborapp.collaborapp.data.entities.UserEntity;
import io.collaborapp.collaborapp.data.respository.BaseFirebaseRepository;
import io.collaborapp.collaborapp.data.respository.ChatRepository;

/**
 * Implementation of DataManager for Chats
 * Created by wilfredonieves on 11/9/17.
 */

public class ChatRepositoryImpl extends BaseFirebaseRepository implements ChatRepository {
    private List<ChatEntity> mChatList;

    private FirebaseDatabase mFirebaseDatabase;

    private DatabaseReference mChatReference;

    public ChatRepositoryImpl(FirebaseDatabase firebaseDatabase) {
        this.mFirebaseDatabase = firebaseDatabase;
    }

    public DatabaseReference getChildReference() {
        if (this.mChatReference == null) {
            this.mChatReference = mFirebaseDatabase.getReference()
                    .child(FIREBASE_CHILD_KEY_USER_CHATS)
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        }
        return mChatReference;

    }

    @Override
    public void createChat() {

    }

    @Override
    public void fetchChats() {
        getChildReference().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatEntity chat = dataSnapshot.getValue(ChatEntity.class);
                mChatList.add(chat);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void addMessage(String chatId, MessageEntity message) {

    }

    @Override
    public void addUsers(String chatId, UserEntity userEntity) {

    }

    @Override
    public void deleteChat(String chatId) {

    }
}
