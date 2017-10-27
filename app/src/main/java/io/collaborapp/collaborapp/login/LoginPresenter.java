package io.collaborapp.collaborapp.login;


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
//TODO: Remove mAuth dependency and move to a data/model class
public class LoginPresenter implements LoginContract.Presenter {
    private final LoginContract.View mLoginView;
    private FirebaseAuth mAuth;

    public LoginPresenter(LoginContract.View loginView) {
        this.mLoginView = loginView;
        mLoginView.setPresenter(this);
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
    public void logInWithFirebaseEmailAndPassword(String email, String password) {
        mLoginView.showProgress();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                processSignInResponse(task);
            }
        });
    }

    @Override
    public void signUpWithFirebaseEmailAndPassword(String email, String password) {
        mLoginView.showProgress();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                processSignInResponse(task);
            }
        });
    }

    private void processSignInResponse(@NonNull Task<AuthResult> task) {
        mLoginView.hideProgress();
        if (task.isSuccessful()) {
            mLoginView.navigateToHome();
        } else {
            mLoginView.showError();
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
