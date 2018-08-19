package io.collaborapp.collaborapp.chat;

import javax.inject.Inject;

import io.collaborapp.collaborapp.BasePresenter;
import io.collaborapp.collaborapp.data.DataManager;
import io.collaborapp.collaborapp.data.db.ChatDbHelper;
import io.collaborapp.collaborapp.data.model.MessageEntity;

/**
 * Created by wilfredonieves on 11/7/17.
 */

public class ChatPresenter extends BasePresenter implements ChatContract.Presenter {

    ChatContract.View mChatView;
    @Inject
    public ChatPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void sendMessage(String chatId, MessageEntity messageEntity) {

    }

    @Override
    public void getMessages(String chatId) {

    }

    @Override
    public void deleteMessages(String[] messageId) {

    }

    @Override
    public void deleteChats() {

    }

    @Override
    public void receiveMessage() {

    }

    @Override
    public void setView(ChatContract.View view) {
        mChatView = view;
    }

}
