package io.collaborapp.collaborapp.login;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import io.collaborapp.collaborapp.BasePresenter;
import io.collaborapp.collaborapp.BaseView;

/**
 * Created by wilfredonieves on 4/28/17.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void showProgress();

        void hideProgress();

        void showError();

        void navigateToHome();
    }

    interface Presenter extends BasePresenter {
        void validateCredentials();

        void logInWithGoogle(GoogleSignInAccount account);

    }
}
