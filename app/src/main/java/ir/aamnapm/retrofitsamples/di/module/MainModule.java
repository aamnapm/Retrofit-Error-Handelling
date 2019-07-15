package ir.aamnapm.retrofitsamples.di.module;

import dagger.Module;
import dagger.Provides;
import ir.aamnapm.retrofitsamples.api.MainApi;
import ir.aamnapm.retrofitsamples.repository.MainRepository;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    static MainApi provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }

    @Provides
    static MainRepository provideMainRepository(MainApi mainApi) {
        return new MainRepository(mainApi);
    }
}
