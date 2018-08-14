package io.collaborapp.collaborapp.di.app;

import android.app.Application;

import javax.inject.Inject;

import io.collaborapp.collaborapp.data.DataManager;
import io.collaborapp.collaborapp.di.authentication.AuthenticationComponent;
import io.collaborapp.collaborapp.di.authentication.AuthenticationModule;
import io.collaborapp.collaborapp.di.chat.ChatComponent;
import io.collaborapp.collaborapp.di.chat.ChatListModule;

/**
 * Created by wilfredonieves on 10/27/17.
 */

public class BaseApplication extends Application {
    private AppComponent mAppComponent;
    private AuthenticationComponent mAuthenticationComponent;
    private ChatComponent mChatComponent;

    @Inject
    DataManager mDataManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = createAppComponent();
    }

    private AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AuthenticationComponent createAuthenticationComponent() {
        mAuthenticationComponent = mAppComponent.plus(new AuthenticationModule());
        return mAuthenticationComponent;
    }

    public ChatComponent createChatComponent(){
        mChatComponent = mAppComponent.plus(new ChatListModule());
        return mChatComponent;
    }

    public void releaseAuthenticationComponent() {
        mAuthenticationComponent = null;
    }
}
