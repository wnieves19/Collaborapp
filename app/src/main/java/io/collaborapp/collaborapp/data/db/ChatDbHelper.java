package io.collaborapp.collaborapp.data.db;

import android.support.annotation.Nullable;

import io.collaborapp.collaborapp.data.model.ChatEntity;
import io.collaborapp.collaborapp.data.model.MessageEntity;
import io.reactivex.Observable;

public interface ChatDbHelper {

    Observable<ChatEntity> createChat(String[] userId, @Nullable String groupName);

    Observable deleteChat(String[] chatId);

    void sendMessage(String chatId, MessageEntity message);

    void deleteMessage(String messageId);
}
