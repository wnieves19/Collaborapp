package io.collaborapp.collaborapp.di.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.collaborapp.collaborapp.chat.ChatMessagesAdapter;
import io.collaborapp.collaborapp.data.AppDataManager;
import io.collaborapp.collaborapp.data.DataManager;
import io.collaborapp.collaborapp.data.db.AuthenticationDbHelper;
import io.collaborapp.collaborapp.data.db.ChatDbHelper;
import io.collaborapp.collaborapp.data.db.impl.AuthenticationDbHelperImpl;
import io.collaborapp.collaborapp.data.db.impl.ChatDbHelperImpl;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by wilfredonieves on 10/27/17.
 */

@Module
public class AppModule {
    private Context context;

    AppModule(Application application) {
        context = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public Resources provideResources(Context context) {
        return context.getResources();
    }

    @Provides
    FirebaseDatabase providesFirebaseDatabase() {
        return FirebaseDatabase.getInstance();
    }

    @Provides
    FirebaseAuth providesFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    SharedPreferences providesSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
    @Provides
    @Singleton
    AuthenticationDbHelper provideAuthDbHelper(AuthenticationDbHelperImpl authDbHelper) {
        return authDbHelper;
    }

    @Provides
    @Singleton
    ChatDbHelper provideChatDbHelper(ChatDbHelperImpl chatDbHelper) {
        return chatDbHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager){
        return appDataManager;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(){
        return new LinearLayoutManager(context);
    }
}

