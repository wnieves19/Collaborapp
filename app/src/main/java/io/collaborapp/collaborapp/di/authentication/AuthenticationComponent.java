package io.collaborapp.collaborapp.di.authentication;

import dagger.Subcomponent;
import io.collaborapp.collaborapp.authentication.AuthenticationActivity;

/**
 * Created by wilfredonieves on 10/27/17.
 */
@AuthenticationScope
@Subcomponent(modules = {AuthenticationModule.class})
public interface AuthenticationComponent {
    void inject(AuthenticationActivity target);
}
