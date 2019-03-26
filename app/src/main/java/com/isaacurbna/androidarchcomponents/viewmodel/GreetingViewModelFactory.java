package com.isaacurbna.androidarchcomponents.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.isaacurbna.androidarchcomponents.interactor.GreetingInteractor;

import javax.inject.Named;

public class GreetingViewModelFactory implements ViewModelProvider.Factory {

    private GreetingInteractor commonGreetingInteractor;
    private GreetingInteractor specialGreetingInteractor;

    public GreetingViewModelFactory(@Named("common") GreetingInteractor commonGreetingInteractor,
                                    @Named("special") GreetingInteractor specialGreetingInteractor) {
        this.commonGreetingInteractor = commonGreetingInteractor;
        this.specialGreetingInteractor = specialGreetingInteractor;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GreetingViewModel.class)) {
            return (T) new GreetingViewModel(commonGreetingInteractor, specialGreetingInteractor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
