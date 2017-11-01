package io.collaborapp.collaborapp.authentication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

/**
 * Created by wilfredonieves on 10/17/17.
 */

public class LoginFragment extends Fragment implements AuthenticationContract.View, View.OnClickListener {
    @BindView(R.id.email)
    EditText mEmail;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.indeterminateBar)
    ProgressBar mProgressBar;


    @Inject
    AuthenticationContract.Presenter mAuthenticationPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        ((BaseApplication) getActivity().getApplication()).createAuthenticationComponent().inject(this);
        view.findViewById(R.id.login).setOnClickListener(this);
        mAuthenticationPresenter.setView(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                mAuthenticationPresenter.logInWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString());
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mAuthenticationPresenter.unsubscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
        mEmail.setError("Enter a valid email");
    }

    @Override
    public void setErrorPasswordField() {
        mPassword.setError("Enter a valid Password");
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

}
