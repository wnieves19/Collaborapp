package io.collaborapp.collaborapp.authentication;


import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import io.collaborapp.collaborapp.BasePresenter;
import io.collaborapp.collaborapp.data.DataManager;
import io.collaborapp.collaborapp.data.db.AuthenticationDbHelper;
import io.collaborapp.collaborapp.firebase.RxFirebase;
import io.collaborapp.collaborapp.rx.DefaultObserver;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wilfredonieves on 10/16/17.
 */
public class AuthenticationPresenter extends BasePresenter implements AuthenticationContract.Presenter {
    private AuthenticationContract.View mAuthenticationView;

    private AuthenticationContract.LogOutView mLogoutView;


    private final static int MIN_CHARS_PASSWORD = 5;

    @Inject
    public AuthenticationPresenter(DataManager dataManager) {
        super(dataManager);
        getDataManager().getAuthUser().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onAuthUser);
    }

    private void onAuthUser(FirebaseUser user) {
        if(user!=null && mAuthenticationView!=null) {
            mAuthenticationView.navigateToHome();
        }
    }

    @Override
    public void logInWithGoogle(GoogleSignInAccount account) {
        mAuthenticationView.showProgress();
        getDataManager().signInWithGoogle(account).map((Function<AuthResult, Object>) authResult -> {
            if (authResult.getUser() != null) {
                Observable<?> observable = getDataManager().createNewUser(authResult.getUser().getUid(), authResult.getUser().getEmail())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
                DisposableObserver observer = new WriteUserObserver();
                observable.subscribeWith(observer);
            } else {
                mAuthenticationView.showError("Google authentication failed");
            }
            return authResult;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

    }

    @Override
    public void logInWithEmailAndPassword(String email, String password) {
        if (!validateFields(email, password, null)) return;
        mAuthenticationView.showProgress();
        getDataManager().signInWithEmail(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onTaskSuccess, this::onLoginFailed);
    }

    @Override
    public void signUpWithEmailAndPassword(String email, String password, String passwordConfirmation) {
        //TODO create user in database after authentication
        if (!validateFields(email, password, passwordConfirmation)) return;
        if (!password.equals(passwordConfirmation)) {
            mAuthenticationView.setErrorPasswordConfirm();
            mAuthenticationView.showError("Password and password confirmation are not the same");
            return;
        }
        mAuthenticationView.showProgress();
        getDataManager().signUpWithEmail(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onTaskSuccess, this::onLoginFailed);
    }

    @Override
    public void setView(AuthenticationContract.View view) {
        this.mAuthenticationView = view;
    }

    @Override
    public void setLogoutView(AuthenticationContract.LogOutView view) {
        this.mLogoutView = view;
    }

    @Override
    public void signOut() {
        getDataManager().signOut();
        mLogoutView.navigateToAuthFragment();
    }

    private boolean validateFields(String email, String password, @Nullable String passwordConfirm) {
        boolean validate = true;
        if (email.isEmpty()) {
            mAuthenticationView.setErrorEmailField();
            validate = false;
        }
        if (password.isEmpty() || password.length() < MIN_CHARS_PASSWORD) {
            mAuthenticationView.setErrorPasswordField();
            validate = false;
        }
        if (passwordConfirm != null && passwordConfirm.isEmpty()) {
            mAuthenticationView.setErrorPasswordConfirm();
        }
        return validate;
    }

    private void onTaskSuccess(AuthResult authResult) {
        mAuthenticationView.hideProgress();
        mAuthenticationView.navigateToHome();
    }

    private void onLoginFailed(Throwable e) {
        mAuthenticationView.hideProgress();
        mAuthenticationView.showError(e.getMessage());
    }

    private final class WriteUserObserver extends DefaultObserver<RxFirebase.FirebaseTaskResponseSuccess> {

        @Override
        public void onComplete() {
            super.onComplete();

        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mAuthenticationView.showError(e.getMessage());
        }

        @Override
        public void onNext(RxFirebase.FirebaseTaskResponseSuccess firebaseTaskResponseSuccess) {
            super.onNext(firebaseTaskResponseSuccess);
            mAuthenticationView.navigateToHome();

        }
    }

}
