package io.collaborapp.collaborapp.di.authentication;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import dagger.Module;
import dagger.Provides;
import io.collaborapp.collaborapp.authentication.AuthenticationContract;
import io.collaborapp.collaborapp.authentication.AuthenticationPresenter;
import io.collaborapp.collaborapp.data.manager.AuthenticationManager;
import io.collaborapp.collaborapp.data.manager.impl.AuthenticationManagerImpl;

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
    AuthenticationManager providesManager(FirebaseAuth firebaseAuth,
                                          FirebaseDatabase firebaseDatabase) {
        return new AuthenticationManagerImpl(firebaseAuth, firebaseDatabase);
    }

}
