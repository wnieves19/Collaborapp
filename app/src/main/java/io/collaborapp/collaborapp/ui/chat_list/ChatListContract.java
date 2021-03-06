package io.collaborapp.collaborapp.ui.chat_list;

import java.util.List;

import javax.annotation.Nullable;

import io.collaborapp.collaborapp.BasePresenter;
import io.collaborapp.collaborapp.base.MvpView;
import io.collaborapp.collaborapp.data.model.ChatEntity;
import io.collaborapp.collaborapp.data.model.UserEntity;

public interface ChatListContract {
    interface View extends MvpView {

        void showChatList(List<ChatEntity> chatList);

        void updateChatList();

        void openChatView(ChatEntity chat);


    }

    interface Presenter extends BasePresenter {
        void deleteChats(String[] chatIds);

        void createChat(List<UserEntity> users, @Nullable String groupName);

        void setView(ChatListContract.View view);


        void muteChat(String chatId);

        void unmuteChat(String chatId);

        List<ChatEntity> getChatList();
    }
}
