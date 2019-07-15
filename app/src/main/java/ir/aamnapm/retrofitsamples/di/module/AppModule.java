package ir.aamnapm.retrofitsamples.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ir.aamnapm.retrofitsamples.rerofitUtils.ErrorHandlingCallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static ir.aamnapm.retrofitsamples.di.Constant.BASE_URL;

@Module
public class AppModule {

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(new ErrorHandlingCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
















