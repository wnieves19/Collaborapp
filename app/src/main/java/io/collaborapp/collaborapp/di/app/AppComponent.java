package io.collaborapp.collaborapp.di.app;

import javax.inject.Singleton;

import dagger.Component;
import io.collaborapp.collaborapp.di.authentication.AuthenticationComponent;
import io.collaborapp.collaborapp.di.authentication.AuthenticationModule;
import io.collaborapp.collaborapp.di.chat.ChatComponent;
import io.collaborapp.collaborapp.di.chat.ChatModule;
import io.collaborapp.collaborapp.utils.NotificationManager;

/**
 * Created by wilfredonieves on 10/27/17.
 */

@Singleton
@Component(modules = {
        AppModule.class})
public interface AppComponent {

    AuthenticationComponent inject(AuthenticationModule authenticationModule);

    ChatComponent inject(ChatModule chatModule);

}
