package io.collaborapp.collaborapp.authentication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.collaborapp.collaborapp.R;

/**
 * Created by wilfredonieves on 10/17/17.
 */

public class LoginFragment extends BaseAuthenticationFragment implements View.OnClickListener{
    @BindView(R.id.email)
    EditText mEmail;
    @BindView(R.id.password)
    EditText mPassword;
    private OnLogInRequestListener onLogInRequestListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container,false);
        ButterKnife.bind(this, view);
        view.findViewById(R.id.login).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                onLogInRequestListener.onLoginRequest(mEmail.getText().toString(), mPassword.getText().toString());
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onLogInRequestListener = (OnLogInRequestListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    void setEmailError() {
        mEmail.setError("Enter a valid email");
    }

    @Override
    void setPasswordError() {
        mPassword.setError("Enter a valid password");
    }

    public interface OnLogInRequestListener {
        void onLoginRequest(String email, String password);
    }
}
