package io.collaborapp.collaborapp.chat;

import javax.inject.Inject;

import io.collaborapp.collaborapp.BasePresenterImpl;
import io.collaborapp.collaborapp.data.DataManager;
import io.collaborapp.collaborapp.data.model.ChatDbUpdate;
import io.collaborapp.collaborapp.data.model.ChatEntity;
import io.collaborapp.collaborapp.data.model.MessageEntity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wilfredonieves on 11/7/17.
 */

public class ChatPresenterImpl extends BasePresenterImpl implements ChatContract.Presenter {

    ChatContract.View mChatView;

    @Inject
    public ChatPresenterImpl(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);

    }

    @Override
    public void onViewInitialized(String chatId) {
        mChatView.updateMessageList(getChat(chatId).getMessageList());

        getCompositeDisposable().add(getChat(chatId).getChatObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(chatDbUpdate -> {
                    switch (chatDbUpdate.getResponse()) {
                        case ChatDbUpdate.NEW_MESSAGE:
                            mChatView.updateMessageList(chatDbUpdate.getChat().getMessageList());
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
                }));
    }

    @Override
    public ChatEntity getChat(String chatId) {
        return getDataManager().getChat(chatId);

    }

    @Override
    public void sendMessage(String chatId, MessageEntity messageEntity) {
        getDataManager().sendMessage(chatId, messageEntity);

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
