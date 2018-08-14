package io.collaborapp.collaborapp.authentication;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import io.collaborapp.collaborapp.BasePresenter;

/**
 * Created by wilfredonieves on 4/28/17.
 */

public interface AuthenticationContract {

    interface View {
        void showProgress();

        void hideProgress();

        void showError(String message);

        void setErrorEmailField();

        void setErrorPasswordField();

        void setErrorPasswordConfirm();

        void navigateToHome();
    }

    interface LogOutView {
        void navigateToAuthFragment();
    }

    interface Presenter {

        void logInWithGoogle(GoogleSignInAccount account);

        void logInWithEmailAndPassword(String email, String password);

        void signUpWithEmailAndPassword(String email, String password, String passwordConfirmation);

        void setView(AuthenticationContract.View view);

        void setLogoutView(AuthenticationContract.LogOutView view);

        void signOut();

    }
}
