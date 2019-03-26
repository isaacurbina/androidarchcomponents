package com.isaacurbna.androidarchcomponents.model;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;

public class CommonGreetingRepo implements GreetingRepo {

    @Override
    public Single<String> getGreeting() {
        return Single.just("Hello from CommonGreetingRepository");
    }
}
