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

import dagger.android.AndroidInjection;
import io.collaborapp.collaborapp.R;
import io.collaborapp.collaborapp.chat.ChatListActivity;
import io.collaborapp.collaborapp.di.app.BaseApplication;

import static android.support.v4.util.Preconditions.checkNotNull;

public class AuthenticationActivity extends AppCompatActivity implements AuthenticationFragment.AuthenticationActionsListener {

    private LoginFragment mLoginFragment;
    private SignUpFragment mSignUpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new AuthenticationFragment())
                    .commit();
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







}
