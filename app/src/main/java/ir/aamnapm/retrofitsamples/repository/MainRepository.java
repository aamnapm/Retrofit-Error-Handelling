package ir.aamnapm.retrofitsamples.repository;

import android.util.Log;

import java.io.IOException;

import javax.inject.Inject;

import ir.aamnapm.retrofitsamples.CallBackApi;
import ir.aamnapm.retrofitsamples.api.MainApi;
import ir.aamnapm.retrofitsamples.model.GetDataResponse;
import ir.aamnapm.retrofitsamples.rerofitUtils.MyCall;
import ir.aamnapm.retrofitsamples.rerofitUtils.MyCallback;
import retrofit2.Response;

public class MainRepository {

    private MainApi mainApi;

    @Inject
    public MainRepository(MainApi mainApi) {
        this.mainApi = mainApi;
    }


    public void callRemoteApi(final CallBackApi callBackApi) {
        Log.e("MainRepository", "callRemoteApi ");
        final MyCall<GetDataResponse> dataResponseMyCall = mainApi.getIp(5);

        dataResponseMyCall.enqueue(new MyCallback<GetDataResponse>() {
            @Override
            public void success(Response<GetDataResponse> response) {
                Log.e("MainRepository", "SUCCESS! " + response.body().getEmail());
                callBackApi.onSuccess(response.body());
            }

            @Override
            public void unauthenticated(Response<?> response) {
                Log.e("MainRepository", "UNAUTHENTICATED");
                callBackApi.unAuthenticated(response.errorBody());
            }

            @Override
            public void clientError(Response<?> response) {
                Log.e("MainRepository", "CLIENT ERROR " + response.code() + " " + response.message());
                callBackApi.clientError(response.code());
            }

            @Override
            public void serverError(Response<?> response) {
                Log.e("MainRepository", "SERVER ERROR " + response.code() + " " + response.message());
                callBackApi.serverError(response);
            }

            @Override
            public void networkError(IOException e) {
                Log.e("MainActivity", "NETWORK ERROR " + e.getMessage());
                callBackApi.networkError(e);
            }

            @Override
            public void unexpectedError(Throwable t) {
                Log.e("MainRepository", "FATAL ERROR " + t.getMessage());
                callBackApi.unexpectedError(t);
            }
        });
    }
}