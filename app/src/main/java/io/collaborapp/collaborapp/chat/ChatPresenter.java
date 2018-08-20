package io.collaborapp.collaborapp.chat;

import javax.inject.Inject;

import io.collaborapp.collaborapp.BasePresenter;
import io.collaborapp.collaborapp.data.DataManager;
import io.collaborapp.collaborapp.data.model.ChatDbUpdate;
import io.collaborapp.collaborapp.data.model.ChatEntity;
import io.collaborapp.collaborapp.data.model.MessageEntity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
    public void onViewInitialized(ChatEntity chatEntity) {
        chatEntity.getChatObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(chatDbUpdate -> {
                    switch (chatDbUpdate.getResponse()) {
                        case ChatDbUpdate.NEW_MESSAGE:
                            mChatView.updateMessageList();
                            break;
                        case ChatDbUpdate.TITLE_CHANGED:

                            break;
                        case ChatDbUpdate.CHAT_MUTED:

                            break;
                        case ChatDbUpdate.MEMBER_ADDED:

                            break;
                        case ChatDbUpdate.MEMBER_REMOVED:

                            break;
                    }
                    mChatView.updateMessageList();
                });
    }

    @Override
    public ChatEntity getChat(String chatId) {
       return getDataManager().getChat(chatId);

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
