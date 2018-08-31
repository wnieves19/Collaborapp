package io.collaborapp.collaborapp.ui.chat;

import java.util.List;

import io.collaborapp.collaborapp.BasePresenter;
import io.collaborapp.collaborapp.data.model.ChatEntity;
import io.collaborapp.collaborapp.data.model.MessageEntity;

/**
 * Created by wilfredonieves on 11/2/17.
 */

public interface ChatContract {
    interface View {
        void updateMessageList(List<MessageEntity> messageList);

        void updateChatTitle(String title);

        void toggleChatMute();

        void updateMemberList();

    }

    interface Presenter extends BasePresenter{
        void sendMessage(String chatId, MessageEntity messageEntity);

        void getMessages(String chatId);

        void deleteMessages(String[] messageId);

        void deleteChats();

        void receiveMessage();

        void onViewInitialized(String chatId);

        ChatEntity getChat(String chatId);

        void setView(View view);
    }
}
