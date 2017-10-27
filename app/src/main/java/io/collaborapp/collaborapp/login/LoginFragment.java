package io.collaborapp.collaborapp.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import io.collaborapp.collaborapp.R;

/**
 * Created by wilfredonieves on 10/17/17.
 */

public class LoginFragment extends Fragment implements View.OnClickListener{
    private EditText mEmail, mPassword;
    private LoginActionsListener onLoginActionsListener;
    private Button mLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container,false);
        mEmail = view.findViewById(R.id.email);
        mPassword = view.findViewById(R.id.password);

        mLogin = view.findViewById(R.id.login);
        mLogin.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                onLoginActionsListener.onLoginRequest(mEmail.getText().toString(), mPassword.getText().toString());
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onLoginActionsListener = (LoginActionsListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
