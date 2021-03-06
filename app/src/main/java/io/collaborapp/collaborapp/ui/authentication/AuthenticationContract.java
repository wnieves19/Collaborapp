package io.collaborapp.collaborapp.ui.authentication;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import io.collaborapp.collaborapp.BasePresenter;
import io.collaborapp.collaborapp.base.MvpView;

/**
 * Created by wilfredonieves on 4/28/17.
 */

public interface AuthenticationContract {

    interface View extends MvpView{

        void showError(String message);

        void setErrorEmailField();

        void setErrorPasswordField();

        void setErrorPasswordConfirm();

        void navigateToHome();
    }

    interface LogOutView {
        void navigateToAuthFragment();
    }

    interface Presenter extends BasePresenter {

        void logInWithGoogle(GoogleSignInAccount account);

        void logInWithEmailAndPassword(String email, String password);

        void signUpWithEmailAndPassword(String email, String password, String passwordConfirmation);

        void setView(AuthenticationContract.View view);

        void setLogoutView(AuthenticationContract.LogOutView view);

        void signOut();

    }
}
