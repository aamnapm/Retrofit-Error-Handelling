package com.aamnapm.aamnapmretrofit;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomCallAdapter<T> implements CustomCall<T> {

    private final Call<T> call;
    private final Executor callbackExecutor;

    public CustomCallAdapter(Call<T> call, Executor callbackExecutor) {
        this.call = call;
        this.callbackExecutor = callbackExecutor;
    }

    @Override
    public void cancel() {
        call.cancel();
    }

    @Override
    public void enqueue(final CustomCallback<T> callback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                // TODO if 'callbackExecutor' is not null, the 'callback' methods should be executed
                // on that executor by submitting a Runnable. This is left as an exercise for the reader.

                int code = response.code();
                callbackExecutor.execute(() -> {

                    if (code >= 200 && code < 300) {
                        callback.success(code, response);
                    } else if (code == 401) {
                        callback.unauthenticated(code, response);
                    } else if (code >= 400 && code < 500) {
                        callback.clientError(code, response);
                    } else if (code >= 500 && code < 600) {
                        callback.serverError(code, response);
                    } else {
                        callback.unexpectedError(new RuntimeException("Unexpected response " + response));
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                // TODO if 'callbackExecutor' is not null, the 'callback' methods should be executed
                // on that executor by submitting a Runnable. This is left as an exercise for the reader.
                callbackExecutor.execute(() -> {

                    if (t instanceof IOException) {
                        callback.networkError((IOException) t);
                    } else {
                        callback.unexpectedError(t);
                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public CustomCall<T> clone() {
        return new CustomCallAdapter<>(call.clone(), callbackExecutor);
    }

}
