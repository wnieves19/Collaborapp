package io.collaborapp.collaborapp.authentication;

import android.support.v4.app.Fragment;

/**
 * Created by wilfredonieves on 10/30/17.
 */

public abstract class BaseAuthenticationFragment extends Fragment {
    abstract void setEmailError();
    abstract void setPasswordError();
}
