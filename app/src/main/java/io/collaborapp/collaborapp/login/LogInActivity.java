package io.collaborapp.collaborapp.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import io.collaborapp.collaborapp.R;
import io.collaborapp.collaborapp.chat.ChatListActivity;

public class LogInActivity extends AppCompatActivity implements LoginContract.View, OnLoginMethodRequestListener {
    private LoginContract.Presenter mLoginPresenter;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginPresenter = new LoginPresenter(this);
        mProgressBar = findViewById(R.id.indeterminateBar);
        LogInOptionsFragment loginFragment = new LogInOptionsFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, loginFragment).commit();

    }

    @Override
    public void onSignUpClicked() {
        SignUpFragment signUpFragment = new SignUpFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, signUpFragment, "sign_up_fragment")
                .addToBackStack("sign_up_fragment")
                .commit();
    }

    @Override
    public void onSignInWithGoogleClicked(GoogleSignInAccount account) {
        mProgressBar.setVisibility(View.VISIBLE);
        mLoginPresenter.logInWithGoogle(account);
    }

    @Override
    public void onLoginClicked() {
        LoginFragment loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, loginFragment, "login_fragment")
                .addToBackStack("login_fragment")
                .commit();
    }

    @Override
    public void onLogInError() {
        mProgressBar.setVisibility(View.GONE);
        showError();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError() {
        mProgressBar.setVisibility(View.GONE);
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Could not login, try later");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public void navigateToHome() {
        mProgressBar.setVisibility(View.GONE);
        startActivity(new Intent(this, ChatListActivity.class));
    }
}
