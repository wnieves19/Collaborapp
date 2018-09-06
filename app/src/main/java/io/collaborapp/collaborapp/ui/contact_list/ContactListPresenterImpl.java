package io.collaborapp.collaborapp.ui.contact_list;

import java.util.List;

import io.collaborapp.collaborapp.BasePresenterImpl;
import io.collaborapp.collaborapp.data.DataManager;
import io.collaborapp.collaborapp.data.model.UserEntity;
import io.reactivex.disposables.CompositeDisposable;

public class ContactListPresenterImpl extends BasePresenterImpl implements ContactListContract.Presenter {
    private ContactListContract.View mView;

    public ContactListPresenterImpl(DataManager mDataManager, CompositeDisposable compositeDisposable) {
        super(mDataManager, compositeDisposable);
    }

    @Override
    public void onViewInitialized() {

    }

    @Override
    public void deleteContact(String[] chatIds) {

    }

    @Override
    public void setView(ContactListContract.View view) {
        this.mView = view;
    }

    @Override
    public void muteChat(String chatId) {

    }

    @Override
    public void unmuteChat(String chatId) {

    }

    @Override
    public List<UserEntity> getChatList() {
        return null;
    }

}
