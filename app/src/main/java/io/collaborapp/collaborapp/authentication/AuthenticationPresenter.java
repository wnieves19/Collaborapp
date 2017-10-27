package io.collaborapp.collaborapp.authentication;


import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * Created by wilfredonieves on 10/16/17.
 */
//TODO: Remove mAuth dependency and move to data/model
public class AuthenticationPresenter implements AuthenticationContract.Presenter {
    private final AuthenticationContract.View mAuthenticationView;
    private FirebaseAuth mAuth;

    public AuthenticationPresenter(AuthenticationContract.View authenticationView) {
        this.mAuthenticationView = authenticationView;
        mAuthenticationView.setPresenter(this);
    }

    @Override
    public void logInWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        processSignInResponse(task);
                    }
                });
    }

    @Override
    public void logInWithEmailAndPassword(String email, String password) {
        mAuthenticationView.showProgress();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                processSignInResponse(task);
            }
        });
    }

    @Override
    public void signUpWithEmailAndPassword(String email, String password) {
        mAuthenticationView.showProgress();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                processSignInResponse(task);
            }
        });
    }

    private void processSignInResponse(@NonNull Task<AuthResult> task) {
        mAuthenticationView.hideProgress();
        if (task.isSuccessful()) {
            mAuthenticationView.navigateToHome();
        } else {
            mAuthenticationView.showError();
        }
    }


    @Override
    public void subscribe() {
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void unsubscribe() {

    }
}
