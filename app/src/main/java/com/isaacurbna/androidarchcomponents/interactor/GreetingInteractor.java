package com.isaacurbna.androidarchcomponents.interactor;

import android.support.annotation.NonNull;

import com.isaacurbna.androidarchcomponents.model.GreetingRepo;

import io.reactivex.Single;

public class GreetingInteractor {

    private final GreetingRepo greetingRepo;

    public GreetingInteractor(@NonNull GreetingRepo greetingRepo) {
        this.greetingRepo = greetingRepo;
    }

    public Single<String> execute() {
        return greetingRepo.getGreeting();
    }
}
