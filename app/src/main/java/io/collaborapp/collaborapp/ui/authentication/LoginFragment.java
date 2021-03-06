package io.collaborapp.collaborapp.ui.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.collaborapp.collaborapp.R;
import io.collaborapp.collaborapp.base.BaseFragment;
import io.collaborapp.collaborapp.ui.chat_list.ChatListActivity;
import io.collaborapp.collaborapp.di.app.BaseApplication;

/**
 * Created by wilfredonieves on 10/17/17.
 */

public class LoginFragment extends BaseFragment implements AuthenticationContract.View {
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
        mAuthenticationPresenter.setView(this);
        return view;
    }

    @OnClick(R.id.login)
    public void login(Button button){
        mAuthenticationPresenter.logInWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString());
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mAuthenticationPresenter.onDetach();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
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
    public void navigateToHome() {
        Intent intent = new Intent(getActivity(), ChatListActivity.class);
        startActivity(intent);
    }

}
