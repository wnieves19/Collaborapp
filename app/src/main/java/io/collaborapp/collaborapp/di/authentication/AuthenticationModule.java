package io.collaborapp.collaborapp.di.authentication;

import dagger.Module;
import dagger.Provides;
import io.collaborapp.collaborapp.authentication.AuthenticationContract;
import io.collaborapp.collaborapp.authentication.AuthenticationPresenter;
import io.collaborapp.collaborapp.data.manager.AuthenticationManager;
import io.collaborapp.collaborapp.data.manager.AuthenticationManagerImpl;

/**
 * Created by wilfredonieves on 10/27/17.
 */
@Module
public class AuthenticationModule {
    @Provides
    @AuthenticationScope
    AuthenticationContract.Presenter providePresenter(AuthenticationManager authenticationManager) {
        return new AuthenticationPresenter(authenticationManager);
    }

    @Provides
    @AuthenticationScope
    AuthenticationManager provideInteractor() {
        return new AuthenticationManagerImpl();
    }
}
