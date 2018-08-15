package io.collaborapp.collaborapp.chat_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.collaborapp.collaborapp.R;
import io.collaborapp.collaborapp.authentication.AuthenticationActivity;
import io.collaborapp.collaborapp.authentication.AuthenticationContract;
import io.collaborapp.collaborapp.di.app.BaseApplication;

public class ChatListActivity extends AppCompatActivity  implements AuthenticationContract.LogOutView{
    @Inject
    AuthenticationContract.Presenter mAuthenticationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ((BaseApplication) getApplication()).createAuthenticationComponent().inject(this);
        mAuthenticationPresenter.setLogoutView(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sign_out:{
                mAuthenticationPresenter.signOut();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void navigateToAuthFragment() {
        Intent intent = new Intent(ChatListActivity.this, AuthenticationActivity.class);
        startActivity(intent);
        finish();
    }

}
