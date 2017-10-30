package io.collaborapp.collaborapp.authentication;


import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthResult;

import io.collaborapp.collaborapp.data.manager.AuthenticationManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wilfredonieves on 10/16/17.
 */
public class AuthenticationPresenter implements AuthenticationContract.Presenter {
    private AuthenticationContract.View mAuthenticationView;

    private AuthenticationManager mAuthManager;

    private Disposable mAuthSubscription;

    public AuthenticationPresenter(AuthenticationManager authManager) {
        this.mAuthManager = authManager;
    }

    @Override
    public void logInWithGoogle(GoogleSignInAccount account) {

        mAuthSubscription = mAuthManager.signInWithGoogle(account)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onTaskSuccess, this::onLoginFailed);

    }

    @Override
    public void logInWithEmailAndPassword(String email, String password) {
        mAuthenticationView.showProgress();
        mAuthSubscription = mAuthManager.signInWithEmail(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onTaskSuccess, this::onLoginFailed);
    }

    @Override
    public void signUpWithEmailAndPassword(String email, String password) {
        mAuthenticationView.showProgress();
        mAuthSubscription = mAuthManager.createNewUser(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onTaskSuccess, this::onLoginFailed);

    }

    @Override
    public void setView(AuthenticationContract.View view) {
        this.mAuthenticationView = view;
    }

    private void onTaskSuccess(AuthResult authResult) {
        mAuthenticationView.hideProgress();
        mAuthenticationView.navigateToHome();
    }

    private void onLoginFailed(Throwable e) {
        mAuthenticationView.hideProgress();
        mAuthenticationView.showError();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
