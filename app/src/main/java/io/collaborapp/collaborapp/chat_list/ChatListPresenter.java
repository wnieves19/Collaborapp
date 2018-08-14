package io.collaborapp.collaborapp.chat_list;

import javax.annotation.Nullable;
import javax.inject.Inject;

import io.collaborapp.collaborapp.BasePresenter;
import io.collaborapp.collaborapp.data.DataManager;
import io.collaborapp.collaborapp.data.db.ChatDbHelper;

public class ChatListPresenter extends BasePresenter implements ChatListContract.Presenter {
    private ChatListContract.View mChatListView;

    @Inject
    public ChatListPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void deleteChats(String[] chatIds) {

    }

    @Override
    public void createChat(String[] userId, @Nullable String groupName) {

    }

    @Override
    public void setView(ChatListContract.View view) {
        mChatListView = view;
    }
}
