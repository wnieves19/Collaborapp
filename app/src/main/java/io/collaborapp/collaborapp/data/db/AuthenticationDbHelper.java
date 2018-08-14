package io.collaborapp.collaborapp.data.db;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.Observable;

/**
 * Created by wilfredonieves on 10/30/17.
 */

public interface AuthenticationDbHelper {

    Observable signInWithGoogle(GoogleSignInAccount account);

    Observable<AuthResult> signInWithEmail(String email, String password);

    Observable<AuthResult> signUpWithEmail(String email, String password);

    Observable<Object> createNewUser(String email, String userId);

    Observable<FirebaseUser> getAuthUser();

    void signOut();
}
