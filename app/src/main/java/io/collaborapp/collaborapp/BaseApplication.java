package io.collaborapp.collaborapp;

import android.app.Application;

import io.collaborapp.collaborapp.authentication.AuthenticationComponent;
import io.collaborapp.collaborapp.authentication.AuthenticationModule;

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

    private io.collaborapp.collaborapp.AppComponent createAppComponent()
    {
        return DaggerAppComponent.builder()
                .appModule(new io.collaborapp.collaborapp.AppModule(this))
                .build();
    }
    public AuthenticationComponent createAuthenticationComponent()
    {
        mAuthenticationComponent = mAppComponent.plus(new AuthenticationModule());
        return mAuthenticationComponent;
    }

    public void releaseDetailsComponent()
    {
        mAuthenticationComponent = null;
    }
}
