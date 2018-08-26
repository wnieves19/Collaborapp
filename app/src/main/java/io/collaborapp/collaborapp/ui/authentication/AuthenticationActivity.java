package io.collaborapp.collaborapp.ui.authentication;

import android.app.NotificationChannel;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import io.collaborapp.collaborapp.R;
import io.collaborapp.collaborapp.utils.NotificationManager;

import static io.collaborapp.collaborapp.utils.NotificationManager.CHANNEL_ID;

public class AuthenticationActivity extends AppCompatActivity implements AuthenticationFragment.AuthenticationActionsListener {

    private LoginFragment mLoginFragment;
    private SignUpFragment mSignUpFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createNotificationChannel();
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

    public void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = android.app.NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            android.app.NotificationManager notificationManager = getSystemService(android.app.NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
