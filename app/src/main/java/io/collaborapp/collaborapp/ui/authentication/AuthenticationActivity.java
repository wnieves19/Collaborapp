package io.collaborapp.collaborapp.ui.authentication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.collaborapp.collaborapp.R;

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
