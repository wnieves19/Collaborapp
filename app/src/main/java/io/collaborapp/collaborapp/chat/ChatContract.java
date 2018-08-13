package io.collaborapp.collaborapp.chat;

import io.collaborapp.collaborapp.data.entities.MessageEntity;

/**
 * Created by wilfredonieves on 11/2/17.
 */

public interface ChatContract {
    interface View {
        void displayMessage(MessageEntity message);

        void removeMessage(MessageEntity message);

        void onSendMessageButtonClicked();


    }

    interface Presenter {
        void sendMessage(String chatId);

        void getMessages(String chatId);

        void getChats();

        void deleteMessages(String[] messageId);

        void deleteChats();
    }
}
