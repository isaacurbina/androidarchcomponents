package com.isaacurbna.androidarchcomponents.di;

import com.isaacurbna.androidarchcomponents.MainActivity;
import com.isaacurbna.androidarchcomponents.interactor.GreetingInteractor;
import com.isaacurbna.androidarchcomponents.model.CommonGreetingRepo;
import com.isaacurbna.androidarchcomponents.model.GreetingRepo;
import com.isaacurbna.androidarchcomponents.model.SpecialGreetingRepo;
import com.isaacurbna.androidarchcomponents.viewmodel.GreetingViewModelFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ApplicationModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeActivityInjector();

    @Provides
    @Singleton
    @Named("common")
    static GreetingRepo provideCommonGreetingRepo() {
        return new CommonGreetingRepo();
    }

    @Provides
    @Singleton
    @Named("special")
    static GreetingRepo provideSpecialGreetingRepo() {
        return new SpecialGreetingRepo();
    }

    @Provides
    @Singleton
    @Named("common")
    static GreetingInteractor provideCommonGreetingInteractor(@Named("common") GreetingRepo greetingRepo) {
        return new GreetingInteractor(greetingRepo);
    }

    @Provides
    @Singleton
    @Named("special")
    static GreetingInteractor provideSpecialGreetingInteractor(@Named("special") GreetingRepo greetingRepo) {
        return new GreetingInteractor(greetingRepo);
    }

    @Provides
    @Singleton
    static GreetingViewModelFactory provideGreetingViewModelFactory(@Named("common") GreetingInteractor commonGreetingInteractor,
                                                                    @Named("special") GreetingInteractor specialGreetingInteractor) {
        return new GreetingViewModelFactory(commonGreetingInteractor, specialGreetingInteractor);
    }
}
