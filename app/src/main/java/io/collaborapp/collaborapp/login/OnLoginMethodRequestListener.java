package io.collaborapp.collaborapp.login;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * Created by wilfredonieves on 10/16/17.
 */

public interface OnLoginMethodRequestListener {
    void onSignUpClicked();
    void onSignInWithGoogleClicked(GoogleSignInAccount account);
    void onLoginClicked();
    void onLogInError();
}
