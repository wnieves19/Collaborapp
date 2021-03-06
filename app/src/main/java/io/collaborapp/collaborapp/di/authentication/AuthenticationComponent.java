package io.collaborapp.collaborapp.di.authentication;

import dagger.Subcomponent;
import io.collaborapp.collaborapp.ui.authentication.AuthenticationFragment;
import io.collaborapp.collaborapp.ui.authentication.LoginFragment;
import io.collaborapp.collaborapp.ui.authentication.SignUpFragment;
import io.collaborapp.collaborapp.ui.chat.ChatMessagesAdapter;
import io.collaborapp.collaborapp.ui.chat_list.ChatListActivity;

/**
 * Created by wilfredonieves on 10/27/17.
 */
@AppScope
@Subcomponent(modules = {AuthenticationModule.class})
public interface AuthenticationComponent {

    void inject(SignUpFragment target);

    void inject(LoginFragment target);

    void inject(AuthenticationFragment target);

    void inject(ChatListActivity target);

    void inject(ChatMessagesAdapter target);
}
