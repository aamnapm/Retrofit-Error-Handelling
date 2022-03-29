package com.aamnapm.aamnapmretrofit;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;


public class ViewModelProviderFactory implements ViewModelProvider.Factory {

    private static final String TAG = "ViewModelProviderFactor";

    private final Map<Class<? extends ViewModel>, ViewModel> creators;

    public ViewModelProviderFactory(Map<Class<? extends ViewModel>, ViewModel> creators) {
        this.creators = creators;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        ViewModel creator = creators.get(modelClass);
        if (creator == null) { // if the viewmodel has not been created

            // loop through the allowable keys (aka allowed classes with the @ViewModelKey)
            for (Map.Entry<Class<? extends ViewModel>, ViewModel> entry : creators.entrySet()) {

                // if it'ErrorHandlingAdapter allowed, set the Provider<ViewModel>
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }

        // if this is not one of the allowed keys, throw exception
        if (creator == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }

        // return the Provider
        try {
            return (T) creator;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}














