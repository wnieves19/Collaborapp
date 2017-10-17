package io.collaborapp.collaborapp.login;


import android.app.Activity;
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

public class LoginPresenter implements LoginContract.Presenter {
    private final LoginContract.View mLoginView;
    private FirebaseAuth mAuth;

    public LoginPresenter(LoginContract.View loginView) {
        this.mLoginView = loginView;

        mLoginView.setPresenter(this);
    }


    @Override
    public void validateCredentials() {

    }

    @Override
    public void logInWithGoogle(GoogleSignInAccount account) {
        mLoginView.showProgress();
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mLoginView.hideProgress();
                        if (task.isSuccessful()) {
                            mLoginView.navigateToHome();
                        } else {
                            mLoginView.showError();
                        }
                    }
                });
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
