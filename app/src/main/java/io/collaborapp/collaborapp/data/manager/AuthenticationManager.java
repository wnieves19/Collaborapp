package io.collaborapp.collaborapp.data.manager;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthResult;

import io.collaborapp.collaborapp.data.entities.UserEntity;
import io.reactivex.Observable;

/**
 * Created by wilfredonieves on 10/30/17.
 */

public interface AuthenticationManager {
    Observable signInWithGoogle(GoogleSignInAccount account);
    Observable<AuthResult> signInWithEmail(String email, String password);
    Observable<AuthResult> signUpWithEmail(String email, String password);

    Observable<Object> createNewUser(String email, String userId);

    void signOut();
}
