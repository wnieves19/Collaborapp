package io.collaborapp.collaborapp.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.collaborapp.collaborapp.R;

public class LogInActivity extends AppCompatActivity implements OnLoginMethodRequestListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
    public void onSignInWithGoogleClicked() {

    }

    @Override
    public void onLoginClicked() {
        LoginFragment loginFragment = new LoginFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, loginFragment, "login_fragment")
                .addToBackStack("login_fragment")
                .commit();
    }
}
