package io.collaborapp.collaborapp.chat_list;

import java.util.ArrayList;

import javax.annotation.Nullable;

import io.collaborapp.collaborapp.data.model.ChatEntity;

public interface ChatListContract {
    interface View{
        void displayChatList(ArrayList<ChatEntity> chatList);

        void updateList();
    }
    interface Presenter{
        void deleteChats(String[] chatIds);

        void createChat(String[] userId, @Nullable String groupName);

        void setView(ChatListContract.View view);
    }
}
