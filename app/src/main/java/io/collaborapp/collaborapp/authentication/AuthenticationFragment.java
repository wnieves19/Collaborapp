package io.collaborapp.collaborapp.authentication;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.collaborapp.collaborapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthenticationFragment extends Fragment implements View.OnClickListener {

    private AuthenticationActionsListener onLoginMethodRequestListener;

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
            onLoginMethodRequestListener = (AuthenticationActionsListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

}
