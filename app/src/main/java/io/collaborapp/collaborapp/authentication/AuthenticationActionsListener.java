package io.collaborapp.collaborapp.authentication;

/**
 * Created by wilfredonieves on 10/16/17.
 */

public interface AuthenticationActionsListener {
    void onSignUpOptionClicked();

    void onLoginOptionClicked();

    void onSignInWithGoogleClicked();

    void onLoginRequest(String email, String password);

    void onSignUpRequest(String email, String password);
}


