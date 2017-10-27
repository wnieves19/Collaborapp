package io.collaborapp.collaborapp.login;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import io.collaborapp.collaborapp.R;

import static io.collaborapp.collaborapp.login.LogInActivity.RC_SIGN_IN;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogInOptionsFragment extends Fragment implements View.OnClickListener {

    private LoginActionsListener onLoginMethodRequestListener;

    public LogInOptionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_options, container, false);

        view.findViewById(R.id.sign_in_with_google).setOnClickListener(this);
        view.findViewById(R.id.sign_up).setOnClickListener(this);
        view.findViewById(R.id.login).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_with_google:
                onLoginMethodRequestListener.onSignInWithGoogleClicked();;
                break;
            case R.id.sign_up:
                onLoginMethodRequestListener.onSignUpOptionClicked();
                break;
            case R.id.login:
                onLoginMethodRequestListener.onLoginOptionClicked();
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onLoginMethodRequestListener = (LoginActionsListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

}
