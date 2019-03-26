package com.isaacurbna.androidarchcomponents.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.isaacurbna.androidarchcomponents.interactor.GreetingInteractor;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GreetingViewModel extends ViewModel {

    private static final String TAG = GreetingViewModel.class.getSimpleName();

    private final GreetingInteractor commonGreetingInteractor;
    private final GreetingInteractor specialGreetingInteractor;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<String> liveData = new MutableLiveData<>();

    @Inject
    public GreetingViewModel(@Named("common") GreetingInteractor commonGreetingInteractor,
                             @Named("special") GreetingInteractor specialGreetingInteractor) {
        this.commonGreetingInteractor = commonGreetingInteractor;
        this.specialGreetingInteractor = specialGreetingInteractor;
    }

    @Override
    protected void onCleared() {
        disposables.clear();
        super.onCleared();
    }

    public void loadCommonGreeting() {
        final Disposable disposable = commonGreetingInteractor
                .execute()
                .subscribeOn(Schedulers.io())
                .map(updatedString -> System.currentTimeMillis() + updatedString)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(text -> Log.i(TAG, "onSubscribe called: " + text))
                .subscribe(
                        liveData::setValue,
                        throwable -> liveData.setValue(throwable.toString())
                );
        disposables.add(disposable);
    }

    public void loadSpecialGreeting() {
        final Disposable disposable = specialGreetingInteractor
                .execute()
                .subscribeOn(Schedulers.io())
                .map(updatedString -> System.currentTimeMillis() + updatedString)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(text -> Log.i(TAG, "onSubscribe called: " + text))
                .subscribe(
                        liveData::setValue,
                        throwable -> liveData.setValue(throwable.toString())
                );
        disposables.add(disposable);
    }

    public MutableLiveData<String> getLiveData() {
        return liveData;
    }
}
