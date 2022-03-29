package ir.aamnapm.retrofitsamples.di.module;

import static ir.aamnapm.retrofitsamples.di.Constant.BASE_URL;

import ir.aamnapm.retrofitsamples.rerofitUtils.ErrorHandlingCallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppModule {

    public static Retrofit provideRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(new ErrorHandlingCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
















