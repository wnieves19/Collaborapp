package io.collaborapp.collaborapp;

import javax.inject.Inject;

import io.collaborapp.collaborapp.data.DataManager;

public class BasePresenter {

    private final DataManager mDataManager;

    @Inject
    public BasePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }
}
