package io.collaborapp.collaborapp.data.db.impl;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.collaborapp.collaborapp.data.db.AuthenticationDbHelper;
import io.collaborapp.collaborapp.data.model.UserEntity;
import io.collaborapp.collaborapp.firebase.RxFirebase;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by wilfredonieves on 10/30/17.
 */
@Singleton
public class AuthenticationDbHelperImpl implements AuthenticationDbHelper {
    private FirebaseAuth mAuth;
    private DatabaseReference childReference = null;
    private FirebaseDatabase mFirebaseDatabase;

    @Inject
    public AuthenticationDbHelperImpl(FirebaseAuth firebaseAuth, FirebaseDatabase firebaseDatabase) {
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
    public Observable<FirebaseUser> getAuthUser() {
        return Observable.create(emitter -> {
            if (mAuth.getCurrentUser() != null) {
                if (!emitter.isDisposed()) {
                    emitter.onNext(mAuth.getCurrentUser());
                }
            }
        });
    }


    @Override
    public void signOut() {
        mAuth.getInstance().signOut();
    }
}
