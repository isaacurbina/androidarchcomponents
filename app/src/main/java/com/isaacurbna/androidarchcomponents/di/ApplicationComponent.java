package com.isaacurbna.androidarchcomponents.di;

import com.isaacurbna.androidarchcomponents.Application;
import com.isaacurbna.androidarchcomponents.MainActivity;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        ApplicationModule.class
})
public interface ApplicationComponent extends AndroidInjector<Application> {

    void inject(MainActivity mainActivity);

    @Component.Builder
    interface Builder {

        ApplicationComponent build();

        @BindsInstance
        Builder bindApplication(Application application);
    }
}
