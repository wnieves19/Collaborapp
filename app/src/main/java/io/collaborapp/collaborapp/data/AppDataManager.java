package io.collaborapp.collaborapp.data;

import android.support.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import javax.inject.Inject;

import io.collaborapp.collaborapp.data.db.AuthenticationDbHelper;
import io.collaborapp.collaborapp.data.db.ChatDbHelper;
import io.collaborapp.collaborapp.data.model.ChatEntity;
import io.collaborapp.collaborapp.data.model.MessageEntity;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public class AppDataManager implements DataManager {
    private final ChatDbHelper mChatDbHelper;
    private final AuthenticationDbHelper mAuthDbHelper;

    @Inject
    public AppDataManager(ChatDbHelper mDbHelper, AuthenticationDbHelper mAuthDbHelper) {
        this.mChatDbHelper = mDbHelper;
        this.mAuthDbHelper = mAuthDbHelper;
    }

    @Override
    public Observable signInWithGoogle(GoogleSignInAccount account) {
        return mAuthDbHelper.signInWithGoogle(account);
    }

    @Override
    public Observable<AuthResult> signInWithEmail(String email, String password) {
        return mAuthDbHelper.signInWithEmail(email, password);
    }

    @Override
    public Observable<AuthResult> signUpWithEmail(String email, String password) {
        return mAuthDbHelper.signUpWithEmail(email, password);
    }

    @Override
    public Observable<Object> createNewUser(String email, String userId) {
        return mAuthDbHelper.createNewUser(email, userId);
    }

    @Override
    public Observable<FirebaseUser> getAuthUser() {
        return mAuthDbHelper.getAuthUser();
    }

    @Override
    public void signOut() {
        mAuthDbHelper.signOut();
    }

    @Override
    public Observable createChat(List<String> userId, @Nullable String groupName) {
        return mChatDbHelper.createChat(userId, groupName);
    }

    @Override
    public Observable<Boolean> deleteChat(String[] chatId) {
        return mChatDbHelper.deleteChat(chatId);
    }

    @Override
    public Flowable<ChatEntity> fetchChatList() {
        return mChatDbHelper.fetchChatList();
    }

    @Override
    public List<ChatEntity> getChatList() {
        return mChatDbHelper.getChatList();
    }

    @Override
    public ChatEntity getChat(String chatId) {
        return mChatDbHelper.getChat(chatId);

    }

    @Override
    public void sendMessage(String chatId, MessageEntity message) {
        mChatDbHelper.sendMessage(chatId, message);
    }

    @Override
    public void deleteMessage(String messageId) {
        mChatDbHelper.deleteMessage(messageId);
    }

}
