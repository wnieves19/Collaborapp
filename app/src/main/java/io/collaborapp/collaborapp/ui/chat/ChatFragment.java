package io.collaborapp.collaborapp.ui.chat;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.collaborapp.collaborapp.R;
import io.collaborapp.collaborapp.data.model.ChatEntity;
import io.collaborapp.collaborapp.data.model.MessageEntity;
import io.collaborapp.collaborapp.di.app.BaseApplication;

import static io.collaborapp.collaborapp.data.model.MessageEntity.MESSAGE_TYPE_IM;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment implements ChatContract.View, ChatMessagesAdapter.OnMessageLongClickListener {

    @Inject
    ChatContract.Presenter mChatPresenter;

    @Inject
    ChatMessagesAdapter messagesAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.chat_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.edit_text_message)
    EditText mMessageEditText;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private String mChatId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ButterKnife.bind(this, view);
        ((BaseApplication) getActivity().getApplication()).createChatComponent().inject(this);
        mChatPresenter.setView(this);
        messagesAdapter.setListener(this);

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(messagesAdapter);
        mChatId = getArguments().getString("chatId");
        mChatPresenter.onViewInitialized(mChatId);

        return view;
    }

    @OnClick(R.id.button_send)
    public void sendMessage() {
        String messageText = mMessageEditText.getText().toString();
        if (messageText.equals("")) return;
        MessageEntity messageEntity = new MessageEntity(messageText, MESSAGE_TYPE_IM);
        mChatPresenter.sendMessage(mChatId, messageEntity);
        mMessageEditText.setText("");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mChatPresenter != null)
            mChatPresenter.onDetach();
    }

    @Override
    public void updateMessageList(List<MessageEntity> messageList) {
        messagesAdapter.addMessages(messageList);
        mRecyclerView.scrollToPosition(messageList.size() - 1);
    }

    @Override
    public void updateChatTitle(String title) {
        mToolbar.setTitle(title);
    }

    @Override
    public void toggleChatMute() {

    }

    @Override
    public void updateMemberList() {

    }

    @Override
    public void onItemLongClicked(ChatEntity chat) {

    }
}
