package io.collaborapp.collaborapp.data.db;

import android.support.annotation.Nullable;

import java.util.List;

import io.collaborapp.collaborapp.data.model.ChatEntity;
import io.collaborapp.collaborapp.data.model.MessageEntity;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface ChatDbHelper {

    Observable<ChatEntity> createChat(String[] userId, @Nullable String groupName);

    Observable<Boolean> deleteChat(String[] chatId);

    Flowable<ChatEntity> fetchChatList();

    List<ChatEntity> getChatList();

    void sendMessage(String chatId, MessageEntity message);

    void deleteMessage(String messageId);
}
