package io.collaborapp.collaborapp.chat;

/**
 * Created by wilfredonieves on 11/2/17.
 */

public interface ChatContract {
    interface View {

    }

    interface Presenter {
        void sendMessage(String chatId);

        void getMessages(String chatId);

        void deleteMessages(String[] messageId);

        void deleteChats();

        void setView(View view);
    }
}
