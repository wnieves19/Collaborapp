package io.collaborapp.collaborapp.ui.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.collaborapp.collaborapp.R;

public class ChatActivity extends AppCompatActivity {
    private ChatFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mFragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString("chatId", getIntent().getStringExtra("chatId"));
        mFragment.setArguments(args);

        if (savedInstanceState != null) {
            //Restore the fragment's instance
            mFragment = (ChatFragment) getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, mFragment)
                    .commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's instance
        getSupportFragmentManager().putFragment(outState, "mContent", mFragment);
    }
}
