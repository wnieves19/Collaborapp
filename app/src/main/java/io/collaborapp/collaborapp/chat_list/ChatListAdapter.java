package io.collaborapp.collaborapp.chat_list;


import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.collaborapp.collaborapp.R;
import io.collaborapp.collaborapp.chat.ChatMessagesAdapter;
import io.collaborapp.collaborapp.data.model.ChatEntity;
import io.collaborapp.collaborapp.data.model.MessageEntity;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatListViewHolder> {

    private OnItemClickListener mListener;

    private List<ChatEntity> mChatList;

    public ChatListAdapter(List<ChatEntity> mChatList) {
        this.mChatList = mChatList;
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
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mChatList.size();
    }

    public void setListener(OnItemClickListener onItemClickListener) {
        this.mListener = onItemClickListener;
    }

    public void addChats(List<ChatEntity> chatList) {
        mChatList.clear();
        mChatList.addAll(chatList);
        notifyDataSetChanged();
    }

    public class ChatListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.chat_name)
        TextView mTextView;
        @BindView(R.id.card_view)
        CardView mCardView;

        public ChatListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            mTextView.setText(mChatList.get(position).getLastMessage().getText());
            mCardView.setOnClickListener(v -> {
                mListener.onItemClick(mChatList.get(position));
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ChatEntity chat);
    }
}