package io.collaborapp.collaborapp.chat;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import io.collaborapp.collaborapp.R;
import io.collaborapp.collaborapp.data.model.ChatEntity;
import io.collaborapp.collaborapp.di.app.BaseApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment implements ChatContract.View {

    @Inject
    ChatContract.Presenter mChatPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        ((BaseApplication) getActivity().getApplication()).createChatComponent().inject(this);
        if (savedInstanceState==null){
            mChatPresenter.setView(this);
            ChatEntity chat = mChatPresenter.getChat(getArguments().getString("chatId"));
            mChatPresenter.onViewInitialized(chat);
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mChatPresenter.onDetach();
    }

    @Override
    public void updateMessageList() {
        Log.d("TEST", "updateMessageList: ");
    }

    @Override
    public void updateChatTitle() {

    }

    @Override
    public void toggleChatMute() {

    }

    @Override
    public void updateMemberList() {

    }
}
