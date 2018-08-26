package io.collaborapp.collaborapp.ui.chat_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.collaborapp.collaborapp.R;
import io.collaborapp.collaborapp.ui.chat.ChatActivity;
import io.collaborapp.collaborapp.data.model.ChatEntity;
import io.collaborapp.collaborapp.di.app.BaseApplication;

public class ChatListFragment extends Fragment implements ChatListContract.View, ChatListAdapter.OnItemClickListener {

    @Inject
    ChatListContract.Presenter mChatListPresenter;

    @BindView(R.id.chat_list_recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    ChatListAdapter mAdapter;

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_chat_list, container, false);
        ButterKnife.bind(this, view);

        if (savedInstanceState == null) {
            ((BaseApplication) getActivity().getApplication()).createChatComponent().inject(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter.setListener(this);
            mRecyclerView.setAdapter(mAdapter);
            mChatListPresenter.setView(this);
            mChatListPresenter.onViewInitialized();
        }
        return view;
    }

    @Override
    public void addChatList(List<ChatEntity> chatList) {
        mAdapter.addChats(chatList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateChatList() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mChatListPresenter != null)
            mChatListPresenter.onDetach();
    }

    @Override
    public void openChatView(ChatEntity chat) {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(ChatEntity chat) {
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        intent.putExtra("chatId", chat.getChatId());
        getActivity().startActivity(intent);
    }


}
