package io.collaborapp.collaborapp.login;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.collaborapp.collaborapp.R;

public class LogInActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment loginFragment = new LoginFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, loginFragment).commit();

    }

}
