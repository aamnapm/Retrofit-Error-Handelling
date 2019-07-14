package ir.aamnapm.retrofitsamples.rerofitUtils;

public interface MyCall<T> {

    void cancel();

    void enqueue(MyCallback<T> callback);

    MyCall<T> clone();

    // Left as an exercise for the reader...
    // TODO MyResponse<T> execute() throws MyHttpException;

}
