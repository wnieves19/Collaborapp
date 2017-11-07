package io.collaborapp.collaborapp.di.app;

import android.app.Application;

import io.collaborapp.collaborapp.di.authentication.AuthenticationComponent;
import io.collaborapp.collaborapp.di.authentication.AuthenticationModule;

/**
 * Created by wilfredonieves on 10/27/17.
 */

public class BaseApplication extends Application {
    private AppComponent mAppComponent;
    private AuthenticationComponent mAuthenticationComponent;

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

    public void releaseAuthenticationComponent() {
        mAuthenticationComponent = null;
    }
}
