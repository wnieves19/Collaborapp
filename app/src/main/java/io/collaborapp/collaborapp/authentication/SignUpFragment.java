package io.collaborapp.collaborapp.authentication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.collaborapp.collaborapp.R;
import io.collaborapp.collaborapp.chat.ChatListActivity;
import io.collaborapp.collaborapp.di.app.BaseApplication;

import static dagger.internal.Preconditions.checkNotNull;


public class SignUpFragment extends Fragment implements AuthenticationContract.View, View.OnClickListener {

    @BindView(R.id.email)
    EditText mEmail;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.confirmPassword)
    EditText mPasswordConfirm;
    @BindView(R.id.indeterminateBar)
    ProgressBar mProgressBar;


    @Inject
    AuthenticationContract.Presenter mAuthenticationPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        view.findViewById(R.id.sign_up).setOnClickListener(this);
        mAuthenticationPresenter.setView(this);
        ((BaseApplication) getActivity().getApplication()).createAuthenticationComponent().inject(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_up:
                mAuthenticationPresenter.signUpWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString(), mPasswordConfirm.getText().toString());
                break;
        }
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
        mEmail.setError("Enter a valid email");
    }

    @Override
    public void setErrorPasswordField() {
        mPassword.setError("Enter a valid password");
    }

    @Override
    public void setErrorPasswordConfirm() {
        mPasswordConfirm.setError("Enter a valid password confirmation");
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
}
