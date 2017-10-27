package io.collaborapp.collaborapp.login;

/**
 * Created by wilfredonieves on 10/16/17.
 */

public interface LoginActionsListener {
    void onSignUpOptionClicked();

    void onLoginOptionClicked();

    void onSignInWithGoogleClicked();

    void onLoginRequest(String email, String password);

    void onSignUpRequest(String email, String password);
}


