package io.collaborapp.collaborapp.base;

public interface MvpView {
    void showLoading();

    void hideLoading();

    void hideKeyboard();

    void showError(String message);
}
