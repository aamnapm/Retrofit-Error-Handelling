package ir.aamnapm.retrofitsamples;

public interface CallBackApi {

    void onSuccess(Object obj);

    void serverError(Object obj);

    void networkError(Object obj);

    void unAuthenticated(Object obj);

    void clientError(int statusCode);

    void unexpectedError(Object obj);

}
