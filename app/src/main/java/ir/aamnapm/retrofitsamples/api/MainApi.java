package ir.aamnapm.retrofitsamples.api;

import com.aamnapm.aamnapmretrofit.CustomCall;

import ir.aamnapm.retrofitsamples.model.GetDataResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MainApi {

    @GET("/users/{id}")
    CustomCall<GetDataResponse> getIp(@Path("id") int id);
}
