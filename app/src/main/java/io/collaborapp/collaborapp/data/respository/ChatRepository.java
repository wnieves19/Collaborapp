package io.collaborapp.collaborapp.data.respository;

import io.collaborapp.collaborapp.data.entities.ChatEntity;
import io.collaborapp.collaborapp.data.entities.MessageEntity;
import io.collaborapp.collaborapp.data.entities.UserEntity;

/**
 * Created by wilfredonieves on 11/9/17.
 */

public interface ChatRepository {

    void createChat();

    void fetchChats();

    void addMessage(String chatId, MessageEntity message);

    void addUsers(String chatId, UserEntity userEntity);

    void deleteChat(String chatId);
}
