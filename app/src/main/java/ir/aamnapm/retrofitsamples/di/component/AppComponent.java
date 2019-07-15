package ir.aamnapm.retrofitsamples.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import ir.aamnapm.retrofitsamples.config.AppController;
import ir.aamnapm.retrofitsamples.di.module.ActivityBuilderModule;
import ir.aamnapm.retrofitsamples.di.module.AppModule;
import ir.aamnapm.retrofitsamples.di.module.ViewModelFactoryModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuilderModule.class,//add all view like fragment and activity to this module
                AppModule.class,//Provides methods you need use in all of the app
                ViewModelFactoryModule.class,
        }
)

public interface AppComponent extends AndroidInjector<AppController> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}







