package io.collaborapp.collaborapp.chat_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.collaborapp.collaborapp.R;
import io.collaborapp.collaborapp.chat.ChatActivity;
import io.collaborapp.collaborapp.data.model.ChatEntity;
import io.collaborapp.collaborapp.di.app.BaseApplication;

public class ChatListFragment extends Fragment implements ChatListContract.View, ChatListAdapter.OnItemClickListener {

    @Inject
    ChatListContract.Presenter mChatListPresenter;

    @BindView(R.id.chat_recycler_view)
    RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;

    private RecyclerView.Adapter mAdapter;

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_chat_list, container, false);
        ButterKnife.bind(this, view);
        ((BaseApplication) getActivity().getApplication()).createChatComponent().inject(this);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ChatListAdapter(mChatListPresenter,this);
        mRecyclerView.setAdapter(mAdapter);
        if (savedInstanceState == null) {
            mChatListPresenter.setView(this);
            mChatListPresenter.onViewInitialized();
        }

        return view;
    }

    @Override
    public void updateChatList() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void openChatView(ChatEntity chat) {
        updateChatList();
    }

    @Override
    public void onItemClick(ChatEntity chat) {
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        intent.putExtra("chatId", chat.getChatId());
        getActivity().startActivity(intent);
    }



}
