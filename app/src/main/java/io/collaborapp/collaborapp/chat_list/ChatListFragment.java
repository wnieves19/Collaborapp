package io.collaborapp.collaborapp.chat_list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import io.collaborapp.collaborapp.data.model.ChatEntity;
import io.collaborapp.collaborapp.di.app.BaseApplication;

public class ChatListFragment extends Fragment implements ChatListContract.View {

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
        mAdapter = new ChatListAdapter();
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
//        updateChatList();
        Toast.makeText(getActivity(), "The chat created was " + chat.getChatId(), Toast.LENGTH_SHORT).show();
        //TODO: Open ChatView
    }

    public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatListViewHolder> {
        @NonNull
        @Override
        public ChatListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_list_item, parent, false);
            ChatListViewHolder vh = new ChatListViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull ChatListViewHolder holder, int position) {
            holder.mTextView.setText(mChatListPresenter.getChatList().get(position).getTitle());
        }

        @Override
        public int getItemCount() {
            return mChatListPresenter.getChatList().size();
        }

        public class ChatListViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;

            public ChatListViewHolder(View itemView) {
                super(itemView);
                mTextView = itemView.findViewById(R.id.chat_name);
            }
        }
    }
}
