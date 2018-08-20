package io.collaborapp.collaborapp.chat_list;


import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.collaborapp.collaborapp.R;
import io.collaborapp.collaborapp.data.model.ChatEntity;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatListViewHolder> {
    private final OnItemClickListener listener;
    private ChatListContract.Presenter mChatListPresenter;

    public ChatListAdapter(ChatListContract.Presenter chatListPresenter, OnItemClickListener listener) {
        this.listener = listener;
        this.mChatListPresenter = chatListPresenter;
    }

    @NonNull
    @Override
    public ChatListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_list_item, parent, false);
        ChatListViewHolder vh = new ChatListViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatListViewHolder holder, int position) {
        holder.mTextView.setText(mChatListPresenter.getChatList().get(position).getLastMessage().getText());
        holder.mCardView.setOnClickListener(v -> {
            listener.onItemClick(mChatListPresenter.getChatList().get(position));
        });

    }

    @Override
    public int getItemCount() {
        return mChatListPresenter.getChatList().size();
    }

    public class ChatListViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public CardView mCardView;

        public ChatListViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.chat_name);
            mCardView = itemView.findViewById(R.id.card_view);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(ChatEntity chat);
    }
}