package io.collaborapp.collaborapp.chat_list;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

import io.collaborapp.collaborapp.BasePresenterImpl;
import io.collaborapp.collaborapp.data.DataManager;
import io.collaborapp.collaborapp.data.model.ChatEntity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ChatListPresenterImpl extends BasePresenterImpl implements ChatListContract.Presenter {
    private ChatListContract.View mChatListView;

    @Inject
    public ChatListPresenterImpl(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void onViewInitialized() {
        mChatListView.addChatList(getChatList());
        getCompositeDisposable().add(getDataManager().fetchChatList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(chatEntity -> {
                    mChatListView.addChatList(getChatList());
                }));
    }

    @Override
    public void createChat(List<String> userId, @Nullable String groupName) {
        getCompositeDisposable().add(getDataManager().createChat(userId, groupName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newChat -> {
                    if (newChat == null) return;
                    mChatListView.openChatView((ChatEntity) newChat);
                }));
    }

    @Override
    public void deleteChats(String[] chatIds) {
        getCompositeDisposable().add(getDataManager().deleteChat(chatIds)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(chatDeleted -> {
                    if (chatDeleted)
                        mChatListView.updateChatList();
                }));

    }

    @Override
    public void muteChat(String chatId) {

    }

    @Override
    public void unmuteChat(String chatId) {

    }

    @Override
    public void setView(ChatListContract.View view) {
        mChatListView = view;
    }

    @Override
    public List<ChatEntity> getChatList() {
        return getDataManager().getChatList();
    }
}
