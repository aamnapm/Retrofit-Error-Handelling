package ir.aamnapm.retrofitsamples.di.module;

import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import ir.aamnapm.retrofitsamples.di.config.ViewModelProviderFactory;


@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelFactory);

}
