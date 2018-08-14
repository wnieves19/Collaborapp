package io.collaborapp.collaborapp.di.authentication;

import dagger.Module;
import dagger.Provides;
import io.collaborapp.collaborapp.authentication.AuthenticationContract;
import io.collaborapp.collaborapp.authentication.AuthenticationPresenter;
import io.collaborapp.collaborapp.data.DataManager;

/**
 * Created by wilfredonieves on 10/27/17.
 */
@Module
public class AuthenticationModule {
    @Provides
    @AppScope
    AuthenticationContract.Presenter providePresenter(DataManager authenticationManager) {
        return new AuthenticationPresenter(authenticationManager);
    }

}
