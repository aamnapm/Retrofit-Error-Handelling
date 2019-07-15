package ir.aamnapm.retrofitsamples.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ir.aamnapm.retrofitsamples.ui.MainActivity;


/**
 * add fragment or activity to this module
 */
@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = {
            ViewModelsMainModule.class
            , MainModule.class
    })
    abstract MainActivity contributeMainActivity();
}
