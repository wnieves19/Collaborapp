package io.collaborapp.collaborapp;

import javax.inject.Singleton;

import dagger.Component;
import io.collaborapp.collaborapp.authentication.AuthenticationComponent;
import io.collaborapp.collaborapp.authentication.AuthenticationModule;

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
