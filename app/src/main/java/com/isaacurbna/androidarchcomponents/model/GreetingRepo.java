package com.isaacurbna.androidarchcomponents.model;

import io.reactivex.Single;

public interface GreetingRepo {

    Single<String> getGreeting();

}
