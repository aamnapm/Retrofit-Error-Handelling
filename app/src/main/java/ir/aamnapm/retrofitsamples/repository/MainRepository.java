package ir.aamnapm.retrofitsamples.repository;

import android.util.Log;

import java.io.IOException;

import ir.aamnapm.retrofitsamples.CallBackApi;
import ir.aamnapm.retrofitsamples.api.MainApi;
import ir.aamnapm.retrofitsamples.di.module.AppModule;
import ir.aamnapm.retrofitsamples.model.GetDataResponse;
import ir.aamnapm.retrofitsamples.rerofitUtils.MyCall;
import ir.aamnapm.retrofitsamples.rerofitUtils.MyCallback;
import retrofit2.Response;

public class MainRepository {

    private MyCall<GetDataResponse> getApi;

    public void cancel() {
        if (getApi == null) {
            Log.e("MainRepository", "Before init your api, you cannot cancel it.");
            return;
        }
        getApi.cancel();
    }

    public void callRemoteApi(final CallBackApi<GetDataResponse> callBackApi) {
        Log.e("MainRepository", "callRemoteApi ");
        getApi = AppModule.provideRetrofitInstance().create(MainApi.class).getIp(5);

        getApi.enqueue(new MyCallback<>() {
            @Override
            public void success(int code, Response<GetDataResponse> response) {
                Log.e("MainRepository", "SUCCESS! ");
                callBackApi.onSuccess(code, response.body());
            }

            @Override
            public void unauthenticated(int code, Response<?> response) {
                Log.e("MainRepository", "UNAUTHENTICATED");
                callBackApi.unAuthenticated(code, response.errorBody());
            }

            @Override
            public void clientError(int code, Response<?> response) {
                Log.e("MainRepository", "CLIENT ERROR " + response.code() + " " + response.message());
                callBackApi.clientError(code, response.code());
            }

            @Override
            public void serverError(int code, Response<?> response) {
                Log.e("MainRepository", "SERVER ERROR " + response.code() + " " + response.message());
                callBackApi.serverError(code, response);
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