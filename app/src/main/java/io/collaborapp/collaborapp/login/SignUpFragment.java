package io.collaborapp.collaborapp.login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import io.collaborapp.collaborapp.R;


public class SignUpFragment extends Fragment implements View.OnClickListener {

    private EditText mEmail, mPassword, mPasswordConfirm;
    private Button mSignUp;
    private LoginActionsListener onLoginActionsListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        mEmail = view.findViewById(R.id.email);
        mPassword = view.findViewById(R.id.password);
        mPasswordConfirm = view.findViewById(R.id.confirmPassword);

        mSignUp = view.findViewById(R.id.sign_up);
        mSignUp.setOnClickListener(this);

        return view;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //TODO: Validate mPassword & mPasswordConfirm are the same
            case R.id.sign_up:
                onLoginActionsListener.onSignUpRequest(mEmail.getText().toString(), mPassword.getText().toString());
                break;
        }
    }
}
