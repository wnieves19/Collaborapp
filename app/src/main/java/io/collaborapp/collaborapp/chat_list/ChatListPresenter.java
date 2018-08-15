package io.collaborapp.collaborapp.chat_list;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

import io.collaborapp.collaborapp.BasePresenter;
import io.collaborapp.collaborapp.data.DataManager;
import io.collaborapp.collaborapp.data.model.ChatEntity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ChatListPresenter extends BasePresenter implements ChatListContract.Presenter {
    private ChatListContract.View mChatListView;

    @Inject
    public ChatListPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onViewInitialized() {
        getDataManager().fetchChatList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(chatEntity -> {
                    mChatListView.updateChatList();
                });
    }

    @Override
    public void deleteChats(String[] chatIds) {
        getDataManager().deleteChat(chatIds)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(chatDeleted -> {
                    if (chatDeleted)
                        mChatListView.updateChatList();
                });

    }

    @Override
    public void createChat(String[] userId, @Nullable String groupName) {

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
