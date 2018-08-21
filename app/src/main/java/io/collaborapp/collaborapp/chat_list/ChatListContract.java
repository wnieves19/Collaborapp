package io.collaborapp.collaborapp.chat_list;

import java.util.List;

import javax.annotation.Nullable;

import io.collaborapp.collaborapp.BasePresenter;
import io.collaborapp.collaborapp.data.model.ChatEntity;

public interface ChatListContract {
    interface View {

        void updateChatList();

        void openChatView(ChatEntity chat);
    }

    interface Presenter extends BasePresenter{
        void deleteChats(String[] chatIds);

        void createChat(List<String> userId, @Nullable String groupName);

        void setView(ChatListContract.View view);

        void onViewInitialized();

        void muteChat(String chatId);

        void unmuteChat(String chatId);

        List<ChatEntity> getChatList();
    }
}
