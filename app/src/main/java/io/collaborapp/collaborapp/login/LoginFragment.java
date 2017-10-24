package io.collaborapp.collaborapp.login;

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
    private Button mLoginButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container,false);
        mEmail = view.findViewById(R.id.email);
        mPassword = view.findViewById(R.id.password);

        mLoginButton = view.findViewById(R.id.login);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                //TODO: call presenter method for logging in
                break;
        }
    }
}
