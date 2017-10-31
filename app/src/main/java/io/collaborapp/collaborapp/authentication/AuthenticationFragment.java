package io.collaborapp.collaborapp.authentication;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.collaborapp.collaborapp.R;
import io.collaborapp.collaborapp.chat.ChatListActivity;
import io.collaborapp.collaborapp.di.app.BaseApplication;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthenticationFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener, AuthenticationContract.View, View.OnClickListener {

    private AuthenticationActionsListener onLoginMethodRequestListener;

    private GoogleApiClient mGoogleApiClient;
    public static final int RC_SIGN_IN = 1988;

    @BindView(R.id.indeterminateBar)
    ProgressBar mProgressBar;

    @Inject
    AuthenticationContract.Presenter mAuthenticationPresenter;

    public AuthenticationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_options, container, false);

        view.findViewById(R.id.sign_in_with_google).setOnClickListener(this);
        view.findViewById(R.id.sign_up).setOnClickListener(this);
        view.findViewById(R.id.login).setOnClickListener(this);
        ButterKnife.bind(this, view);
        ((BaseApplication) getActivity().getApplication()).createAuthenticationComponent().inject(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mAuthenticationPresenter.setView(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_with_google:
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
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
            onLoginMethodRequestListener = (AuthenticationActionsListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
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
    public void onResume() {
        super.onResume();
        mAuthenticationPresenter.subscribe();
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
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> dialog.dismiss());
        alertDialog.show();
    }

    @Override
    public void setErrorEmailField() {
        //Ignored intentionally
    }

    @Override
    public void setErrorPasswordField() {
        //Ignored intentionally
    }

    @Override
    public void setErrorPasswordConfirm() {
        //Ignored intentionally
    }

    @Override
    public void setPresenter(AuthenticationContract.Presenter presenter) {
        mAuthenticationPresenter = checkNotNull(presenter, "Presenter cannot be null");

    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(getActivity(), ChatListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getActivity(), "Could not login, try again.", Toast.LENGTH_SHORT).show();
    }

    private void disconnectApiClient() {
        mGoogleApiClient.stopAutoManage(getActivity());
        mGoogleApiClient.disconnect();
    }

    public interface AuthenticationActionsListener {
        void onSignUpOptionClicked();

        void onLoginOptionClicked();
    }

}
