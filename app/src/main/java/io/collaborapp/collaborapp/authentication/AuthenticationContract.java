package io.collaborapp.collaborapp.authentication;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import io.collaborapp.collaborapp.BasePresenter;
import io.collaborapp.collaborapp.BaseView;

/**
 * Created by wilfredonieves on 4/28/17.
 */

public interface AuthenticationContract {

    interface View extends BaseView<Presenter> {
        void showProgress();

        void hideProgress();

        void showError();

        void setPresenter(AuthenticationContract.Presenter presenter);

        void navigateToHome();
    }

    interface Presenter extends BasePresenter {

        void logInWithGoogle(GoogleSignInAccount account);

        void logInWithEmailAndPassword(String email, String password);

        void signUpWithEmailAndPassword(String email, String password);

    }
}
