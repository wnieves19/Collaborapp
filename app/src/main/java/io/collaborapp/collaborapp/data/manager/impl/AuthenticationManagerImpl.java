package io.collaborapp.collaborapp.data.manager.impl;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.collaborapp.collaborapp.data.entities.UserEntity;
import io.collaborapp.collaborapp.data.manager.AuthenticationManager;
import io.collaborapp.collaborapp.firebase.RxFirebase;
import io.reactivex.Observable;

/**
 * Created by wilfredonieves on 10/30/17.
 */

public class AuthenticationManagerImpl implements AuthenticationManager {
    private FirebaseAuth mAuth;
    private DatabaseReference childReference = null;
    private FirebaseDatabase mFirebaseDatabase;


    public AuthenticationManagerImpl(FirebaseAuth firebaseAuth,
                                     FirebaseDatabase firebaseDatabase) {
        this.mAuth = firebaseAuth;
        this.mFirebaseDatabase = firebaseDatabase;
    }

    public DatabaseReference getChildReference() {
        if (this.childReference == null) {
            this.childReference = this.mFirebaseDatabase.
                    getReference()
                    .child("users")
                    .child(this.mAuth.getCurrentUser().getUid());
        }

        return childReference;
    }

    @Override
    public Observable signInWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        return RxFirebase.getObservable(mAuth.signInWithCredential(credential));
    }

    @Override
    public Observable<AuthResult> signInWithEmail(String email, String password) {
        return RxFirebase.getObservable(mAuth.signInWithEmailAndPassword(email, password));
    }

    @Override
    public Observable<AuthResult> signUpWithEmail(String email, String password) {
        return RxFirebase.getObservable(mAuth.createUserWithEmailAndPassword(email, password));
    }

    @Override
    public Observable<Object> createNewUser(String userId, String email) {
        UserEntity userEntity = new UserEntity(userId, email);
        return RxFirebase.getObservable(getChildReference().setValue(userEntity), new RxFirebase.FirebaseTaskResponseSuccess());

    }

    @Override
    public void signOut() {
        mAuth.getInstance().signOut();
    }
}
