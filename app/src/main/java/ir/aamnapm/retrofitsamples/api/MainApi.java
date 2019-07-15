package ir.aamnapm.retrofitsamples.api;

import ir.aamnapm.retrofitsamples.model.GetDataResponse;
import ir.aamnapm.retrofitsamples.rerofitUtils.MyCall;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MainApi {

    @GET("/users/{id}")
    MyCall<GetDataResponse> getIp(@Path("id") int id);
}
