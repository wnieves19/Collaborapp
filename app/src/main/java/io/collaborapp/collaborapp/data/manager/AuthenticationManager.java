package io.collaborapp.collaborapp.data.manager;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthResult;

import io.reactivex.Observable;

/**
 * Created by wilfredonieves on 10/30/17.
 */

public interface AuthenticationManager {
    Observable<AuthResult> signInWithGoogle(GoogleSignInAccount account);
    Observable<AuthResult> signInWithEmail(String email, String password);
    Observable<AuthResult> createNewUser(String email, String password);
    void signOut();
}
