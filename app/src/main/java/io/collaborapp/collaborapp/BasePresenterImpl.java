package io.collaborapp.collaborapp;

import javax.inject.Inject;

import io.collaborapp.collaborapp.data.DataManager;
import io.reactivex.disposables.CompositeDisposable;

public class BasePresenterImpl implements BasePresenter {

    private final DataManager mDataManager;

    private final CompositeDisposable mCompositeDisposable;

    @Inject
    public BasePresenterImpl(DataManager mDataManager, CompositeDisposable compositeDisposable) {
        this.mDataManager = mDataManager;
        this.mCompositeDisposable = compositeDisposable;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
    }
}
