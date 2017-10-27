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

        void setPresenter(LoginContract.Presenter presenter);

        void navigateToHome();
    }

    interface Presenter extends BasePresenter {

        void logInWithGoogle(GoogleSignInAccount account);

        void logInWithFirebaseEmailAndPassword(String email, String password);

        void signUpWithFirebaseEmailAndPassword(String email, String password);

    }
}
