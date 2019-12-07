package com.example.loterias.base;

public interface BaseView<T> {
    void setPresenter(T presenter);
    void genericError(String error);
    void onSuccess();
}
