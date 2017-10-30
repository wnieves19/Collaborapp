package io.collaborapp.collaborapp.authentication;

import dagger.Subcomponent;

/**
 * Created by wilfredonieves on 10/27/17.
 */
@AuthenticationScope
@Subcomponent(modules = {AuthenticationModule.class})
public interface AuthenticationComponent {

    void inject(AuthenticationActivity target);

}
