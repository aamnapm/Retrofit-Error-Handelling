package ir.aamnapm.retrofitsamples.rerofitUtils;

import androidx.annotation.NonNull;

public interface MyCall<T> {

    void cancel();

    void enqueue(MyCallback<T> callback);

    @NonNull
    MyCall<T> clone();
}
