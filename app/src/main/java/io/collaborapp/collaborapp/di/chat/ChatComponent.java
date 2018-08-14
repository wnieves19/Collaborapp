package io.collaborapp.collaborapp.di.chat;

import dagger.Subcomponent;
import io.collaborapp.collaborapp.chat.ChatFragment;
import io.collaborapp.collaborapp.chat_list.ChatListFragment;
import io.collaborapp.collaborapp.di.authentication.AppScope;

@AppScope
@Subcomponent(modules = {ChatListModule.class})
public interface ChatComponent {
    void inject(ChatListFragment target);

    void inject(ChatFragment target);
}
