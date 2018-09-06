package io.collaborapp.collaborapp.ui.contact_list;

import java.util.List;

import io.collaborapp.collaborapp.BasePresenter;
import io.collaborapp.collaborapp.base.MvpView;
import io.collaborapp.collaborapp.data.model.UserEntity;

public interface ContactListContract {

    interface View extends MvpView {

        void showContactList(List<UserEntity> contactList);

        void updateContactList();

        void openContactView(UserEntity contact);

        void openChatViewWithContact(UserEntity contact);


    }

    interface Presenter extends BasePresenter {
        void deleteContact(String[] chatIds);

        void setView(ContactListContract.View view);

        void onViewInitialized();

        void muteChat(String chatId);

        void unmuteChat(String chatId);

        List<UserEntity> getChatList();
    }
}
