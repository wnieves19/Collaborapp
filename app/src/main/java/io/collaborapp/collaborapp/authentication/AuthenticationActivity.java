package io.collaborapp.collaborapp.authentication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Inject;

import io.collaborapp.collaborapp.R;
import io.collaborapp.collaborapp.chat.ChatListActivity;
import io.collaborapp.collaborapp.di.app.BaseApplication;

import static android.support.v4.util.Preconditions.checkNotNull;

public class AuthenticationActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        AuthenticationContract.View,
        LoginFragment.OnLogInRequestListener,
        SignUpFragment.OnSignUpRequestListener,
        AuthenticationFragment.AuthenticationActionsListener {

    @Inject
    AuthenticationContract.Presenter mAuthenticationPresenter;

    private ProgressBar mProgressBar;
    private GoogleApiClient mGoogleApiClient;
    public static final int RC_SIGN_IN = 1988;

    private BaseAuthenticationFragment mLoginFragment;
    private BaseAuthenticationFragment mSignUpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((BaseApplication) getApplication()).createAuthenticationComponent().inject(this);
        mProgressBar = findViewById(R.id.indeterminateBar);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mAuthenticationPresenter.setView(this);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new AuthenticationFragment())
                    .commit();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                mAuthenticationPresenter.logInWithGoogle(account);
            } else {
                showError("Could not login with Google account.");
            }
        }
    }

    @Override
    public void onSignUpOptionClicked() {
        mSignUpFragment = new SignUpFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, mSignUpFragment, "sign_up_fragment")
                .addToBackStack("sign_up_fragment")
                .commit();
    }

    @Override
    public void onLoginOptionClicked() {
        mLoginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, mLoginFragment, "login_fragment")
                .addToBackStack("login_fragment")
                .commit();
    }

    @Override
    public void onSignInWithGoogleClicked() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        showProgress();
    }

    @Override
    public void onLoginRequest(String email, String password) {
        mAuthenticationPresenter.logInWithEmailAndPassword(email, password);
    }

    @Override
    public void onSignUpRequest(String email, String password, String passwordConfirmation) {
        mAuthenticationPresenter.signUpWithEmailAndPassword(email, password, passwordConfirmation);
    }

    @Override
    public void setPresenter(AuthenticationContract.Presenter presenter) {
        mAuthenticationPresenter = checkNotNull(presenter, "Presenter cannot be null");
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        hideProgress();
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> dialog.dismiss());
        alertDialog.show();
    }

    @Override
    public void setErrorEmailField() {
        ((BaseAuthenticationFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container)).setEmailError();
    }

    @Override
    public void setErrorPasswordField() {
        ((BaseAuthenticationFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container)).setPasswordError();
    }

    @Override
    public void setErrorPasswordConfirm() {
        ((SignUpFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_container)).setPasswordConfirmError();
    }

    @Override
    public void onPause() {
        super.onPause();
        disconnectApiClient();
        mAuthenticationPresenter.unsubscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disconnectApiClient();
        mAuthenticationPresenter.unsubscribe();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAuthenticationPresenter.subscribe();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Could not login, try again.", Toast.LENGTH_SHORT).show();
    }


    private void disconnectApiClient() {
        mGoogleApiClient.stopAutoManage(this);
        mGoogleApiClient.disconnect();
    }

    @Override
    public void navigateToHome() {
        hideProgress();
        startActivity(new Intent(this, ChatListActivity.class));
    }

}
