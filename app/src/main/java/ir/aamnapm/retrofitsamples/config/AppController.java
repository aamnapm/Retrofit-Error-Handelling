package ir.aamnapm.retrofitsamples.config;


import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import ir.aamnapm.retrofitsamples.di.component.DaggerAppComponent;


public class AppController extends DaggerApplication {


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
//        return null;//unComment for first build
    }
}






