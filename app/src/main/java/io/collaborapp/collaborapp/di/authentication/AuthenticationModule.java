package io.collaborapp.collaborapp.di.authentication;

import dagger.Module;
import dagger.Provides;
import io.collaborapp.collaborapp.ui.authentication.AuthenticationContract;
import io.collaborapp.collaborapp.ui.authentication.AuthenticationPresenterImpl;
import io.collaborapp.collaborapp.data.DataManager;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by wilfredonieves on 10/27/17.
 */
@Module
public class AuthenticationModule {
    @Provides
    @AppScope
    AuthenticationContract.Presenter providePresenter(DataManager authenticationManager, CompositeDisposable compositeDisposable) {
        return new AuthenticationPresenterImpl(authenticationManager, compositeDisposable);
    }

}
