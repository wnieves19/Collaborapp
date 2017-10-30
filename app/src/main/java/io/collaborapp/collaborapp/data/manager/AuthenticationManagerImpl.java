package io.collaborapp.collaborapp.data.manager;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import io.collaborapp.collaborapp.firebase.RxFirebase;
import io.reactivex.Observable;

/**
 * Created by wilfredonieves on 10/30/17.
 */

public class AuthenticationManagerImpl implements AuthenticationManager {
    private FirebaseAuth mAuth;

    public AuthenticationManagerImpl() {
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public Observable<AuthResult> signInWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        return RxFirebase.getObservable(mAuth.signInWithCredential(credential));
    }

    @Override
    public Observable<AuthResult> signInWithEmail(String email, String password) {
        return RxFirebase.getObservable(mAuth.signInWithEmailAndPassword(email, password));
    }

    @Override
    public Observable<AuthResult> createNewUser(String email, String password) {
        return RxFirebase.getObservable(mAuth.createUserWithEmailAndPassword(email, password));
    }

    @Override
    public void signOut() {
        mAuth.getInstance().signOut();
    }
}
