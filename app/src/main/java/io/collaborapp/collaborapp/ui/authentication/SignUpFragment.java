package io.collaborapp.collaborapp.ui.authentication;

import android.content.Intent;
import android.os.Bundle;
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

public class SignUpFragment extends BaseFragment implements AuthenticationContract.View {

    @BindView(R.id.email)
    EditText mEmail;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.confirmPassword)
    EditText mPasswordConfirm;
    @Inject
    AuthenticationContract.Presenter mAuthenticationPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        ((BaseApplication) getActivity().getApplication()).createAuthenticationComponent().inject(this);
        mAuthenticationPresenter.setView(this);
        return view;
    }

    @OnClick(R.id.sign_up)
    public void signUp(Button button) {
        mAuthenticationPresenter.signUpWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString(), mPasswordConfirm.getText().toString());

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mAuthenticationPresenter.onDetach();
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
    public void navigateToHome() {
        Intent intent = new Intent(getActivity(), ChatListActivity.class);
        startActivity(intent);
    }
}
