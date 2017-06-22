package io.collaborapp.collaborapp.login;

import io.collaborapp.collaborapp.BasePresenter;
import io.collaborapp.collaborapp.BaseView;

/**
 * Created by wilfredonieves on 4/28/17.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void showProgress();

        void hideProgress();

        void showUsernameError();

        void showPasswordError();

        void navigateToHome();
    }

    interface Presenter extends BasePresenter {
        void validateCredentials();

        void logInWithGoogle();

        void logInWithFacebook();

    }
}
