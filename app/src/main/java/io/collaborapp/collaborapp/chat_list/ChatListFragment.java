package io.collaborapp.collaborapp.chat_list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import io.collaborapp.collaborapp.R;
import io.collaborapp.collaborapp.data.model.ChatEntity;
import io.collaborapp.collaborapp.di.app.BaseApplication;

public class ChatListFragment extends Fragment implements ChatListContract.View {

    @Inject
    ChatListContract.Presenter mChatListPresenter;

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        android.view.View view  = inflater.inflate(R.layout.content_chat_list, container, false);
        ((BaseApplication) getActivity().getApplication()).createChatComponent().inject(this);

        mChatListPresenter.setView(this);

        return view;
    }

    @Override
    public void displayChatList(ArrayList<ChatEntity> chatList) {

    }

    @Override
    public void updateList() {

    }
}
