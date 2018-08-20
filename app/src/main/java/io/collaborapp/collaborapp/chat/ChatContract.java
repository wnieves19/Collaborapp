package io.collaborapp.collaborapp.chat;

import io.collaborapp.collaborapp.data.model.ChatEntity;
import io.collaborapp.collaborapp.data.model.MessageEntity;

/**
 * Created by wilfredonieves on 11/2/17.
 */

public interface ChatContract {
    interface View {
        void updateMessageList();

        void updateChatTitle();

        void toggleChatMute();

        void updateMemberList();

    }

    interface Presenter {
        void sendMessage(String chatId, MessageEntity messageEntity);

        void getMessages(String chatId);

        void deleteMessages(String[] messageId);

        void deleteChats();

        void receiveMessage();

        void onViewInitialized(ChatEntity chatEntity);

        ChatEntity getChat(String chatId);

        void setView(View view);
    }
}
