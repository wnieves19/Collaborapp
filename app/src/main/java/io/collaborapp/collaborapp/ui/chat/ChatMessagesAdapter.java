package io.collaborapp.collaborapp.ui.chat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.collaborapp.collaborapp.R;
import io.collaborapp.collaborapp.data.model.ChatEntity;
import io.collaborapp.collaborapp.data.model.MessageEntity;
import io.collaborapp.collaborapp.utils.TimeFormatter;

import static io.collaborapp.collaborapp.data.model.MessageEntity.MESSAGE_TYPE_IM;

public class ChatMessagesAdapter extends RecyclerView.Adapter<ChatMessagesAdapter.ChatMessagesViewHolder> {

    private List<MessageEntity> mMessagesList;

    private OnMessageLongClickListener messageLongClickListener;

    private TimeFormatter mTimeFormatter = new TimeFormatter();

    @Inject
    Context context;

    public ChatMessagesAdapter(List<MessageEntity> mMessagesList) {
        this.mMessagesList = mMessagesList;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatMessagesViewHolder holder, int position) {
        holder.onBind(position);
    }

    @NonNull
    @Override
    public ChatMessagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_message_item_sent, parent, false);
                break;
            case 2:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message_sent, parent, false);
                break;
            case 3:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_message_item_receive, parent, false);
                break;
            case 4:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message_receive_with_media, parent, false);
                break;
        }

        ChatMessagesViewHolder chatMessageViewHolder = new ChatMessagesViewHolder(view);

        return chatMessageViewHolder;
    }

    @Override
    public int getItemCount() {
        return mMessagesList.size();
    }

    @Override
    public int getItemViewType(int position) {
        MessageEntity message = mMessagesList.get(position);
        String authUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (message.getFrom().equals(authUserId)) {
            //Current user text message
            if (message.getType().equals(MESSAGE_TYPE_IM)) {
                return 1;
            }
            //Current user multimedia message
            return 2;
        } else if (message.getType().equals(MESSAGE_TYPE_IM)) {
            //Contact text message
            return 3;
        }
        //Contact multimedia message
        return 4;
    }

    public void setListener(OnMessageLongClickListener messageLongClickListener) {
        this.messageLongClickListener = messageLongClickListener;
    }

    public void addMessages(List<MessageEntity> messageList) {
        mMessagesList.clear();
        mMessagesList.addAll(messageList);
        notifyDataSetChanged();
    }

    public class ChatMessagesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.message_text)
        TextView mTextView;

        @BindView(R.id.message_date)
        TextView mMessageDate;

        public ChatMessagesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int positon) {
            mTextView.setText(mMessagesList.get(positon).getText());
            mMessageDate.setText(mTimeFormatter.formatTime(mMessagesList.get(positon).getCreatedAt()));
        }
    }

    public interface OnMessageLongClickListener {
        void onItemLongClicked(ChatEntity chat);
    }
}
