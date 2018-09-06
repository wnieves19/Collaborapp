package io.collaborapp.collaborapp.ui.contact_list;


import android.os.Bundle;
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
import io.collaborapp.collaborapp.base.BaseFragment;
import io.collaborapp.collaborapp.data.model.UserEntity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends BaseFragment implements ContactListContract.View{

    @BindView(R.id.contact_list_recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    LinearLayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void showContactList(List<UserEntity> contactList) {

    }

    @Override
    public void updateContactList() {

    }

    @Override
    public void openContactView(UserEntity contact) {

    }

    @Override
    public void openChatViewWithContact(UserEntity contact) {

    }
}
