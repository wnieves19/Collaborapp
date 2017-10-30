package io.collaborapp.collaborapp.di.app;

import javax.inject.Singleton;

import dagger.Component;
import io.collaborapp.collaborapp.di.authentication.AuthenticationComponent;
import io.collaborapp.collaborapp.di.authentication.AuthenticationModule;

/**
 * Created by wilfredonieves on 10/27/17.
 */

@Singleton
@Component(modules = {
        AppModule.class})
public interface AppComponent
{
    AuthenticationComponent plus(AuthenticationModule authenticationModule);
}
