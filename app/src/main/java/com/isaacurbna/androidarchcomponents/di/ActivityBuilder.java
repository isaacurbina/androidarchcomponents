package com.isaacurbna.androidarchcomponents.di;

import com.isaacurbna.androidarchcomponents.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = ApplicationModule.class)
    abstract MainActivity contributeActivityInjector();

}
