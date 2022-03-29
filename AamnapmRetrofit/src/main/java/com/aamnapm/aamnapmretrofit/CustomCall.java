package com.aamnapm.aamnapmretrofit;

import androidx.annotation.NonNull;

public interface CustomCall<T> {

    void cancel();

    void enqueue(CustomCallback<T> callback);

    @NonNull
    CustomCall<T> clone();
}
