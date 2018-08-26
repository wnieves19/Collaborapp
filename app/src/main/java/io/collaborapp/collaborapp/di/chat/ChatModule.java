package io.collaborapp.collaborapp.di.chat;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.collaborapp.collaborapp.ui.chat.ChatContract;
import io.collaborapp.collaborapp.ui.chat.ChatMessagesAdapter;
import io.collaborapp.collaborapp.ui.chat.ChatPresenterImpl;
import io.collaborapp.collaborapp.ui.chat_list.ChatListAdapter;
import io.collaborapp.collaborapp.ui.chat_list.ChatListContract;
import io.collaborapp.collaborapp.ui.chat_list.ChatListPresenterImpl;
import io.collaborapp.collaborapp.data.DataManager;
import io.collaborapp.collaborapp.di.authentication.AppScope;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ChatModule {
    @Provides
    @AppScope
    ChatListContract.Presenter providePresenter(DataManager chatManager, CompositeDisposable compositeDisposable) {
        return new ChatListPresenterImpl(chatManager, compositeDisposable);
    }

    @Provides
    @AppScope
    ChatContract.Presenter provideChatPresenter(DataManager chatManager, CompositeDisposable compositeDisposable) {
        return new ChatPresenterImpl(chatManager, compositeDisposable);
    }

    @Provides
    ChatMessagesAdapter providesChatMessageAdapter() {
        return new ChatMessagesAdapter(new ArrayList<>());
    }

    @Provides
    ChatListAdapter providesChatListAdapter() {
        return new ChatListAdapter(new ArrayList<>());
    }
}
