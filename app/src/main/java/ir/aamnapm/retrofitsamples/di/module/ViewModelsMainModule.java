package ir.aamnapm.retrofitsamples.di.module;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import ir.aamnapm.retrofitsamples.di.config.ViewModelKey;
import ir.aamnapm.retrofitsamples.ui.MainViewModel;

@Module
public abstract class ViewModelsMainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindMainViewModel(MainViewModel viewModel);

}
