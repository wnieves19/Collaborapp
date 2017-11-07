package io.collaborapp.collaborapp.di.authentication;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import dagger.Subcomponent;
import io.collaborapp.collaborapp.authentication.AuthenticationFragment;
import io.collaborapp.collaborapp.authentication.LoginFragment;
import io.collaborapp.collaborapp.authentication.SignUpFragment;
import io.collaborapp.collaborapp.data.manager.AuthenticationManager;
import io.collaborapp.collaborapp.data.manager.AuthenticationManagerImpl;

/**
 * Created by wilfredonieves on 10/27/17.
 */
@AuthenticationScope
@Subcomponent(modules = {AuthenticationModule.class})
public interface AuthenticationComponent {

    void inject(SignUpFragment target);

    void inject(LoginFragment target);

    void inject(AuthenticationFragment target);

}
