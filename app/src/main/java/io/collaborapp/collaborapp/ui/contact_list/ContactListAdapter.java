package io.collaborapp.collaborapp.ui.contact_list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import io.collaborapp.collaborapp.data.model.UserEntity;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder> {

    private OnContactClickListener mListener;

    @NonNull
    @Override
    public ContactListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public void setmListener(OnContactClickListener mListener) {
        this.mListener = mListener;
    }
    public class ContactListViewHolder extends RecyclerView.ViewHolder {

        public ContactListViewHolder(View itemView) {
            super(itemView);
        }
    }
    public interface OnContactClickListener {
        void onContactClick(UserEntity user);
    }
}
