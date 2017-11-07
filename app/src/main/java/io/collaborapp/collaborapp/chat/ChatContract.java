package io.collaborapp.collaborapp.chat;

import io.collaborapp.collaborapp.BasePresenter;
import io.collaborapp.collaborapp.BaseView;
import io.collaborapp.collaborapp.data.entities.MessageEntity;

/**
 * Created by wilfredonieves on 11/2/17.
 */

public interface ChatContract {
    interface View extends BaseView<Presenter>{
        void displayMessage(MessageEntity message);

        void removeMessage(MessageEntity message);

        void onSendMessageButtonClicked();


    }
    interface Presenter extends BasePresenter {
        void sendMessage();

        void getMessages();

        void getChats();

        void deleteMessage();
    }
}
