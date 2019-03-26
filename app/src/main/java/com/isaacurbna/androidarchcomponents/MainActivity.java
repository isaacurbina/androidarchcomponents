package com.isaacurbna.androidarchcomponents;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.isaacurbna.androidarchcomponents.di.ApplicationComponent;
import com.isaacurbna.androidarchcomponents.viewmodel.GreetingViewModel;
import com.isaacurbna.androidarchcomponents.viewmodel.GreetingViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    @Inject
    GreetingViewModelFactory viewModelFactory;

    @BindView(R.id.greeting_textview)
    TextView textView;

    private GreetingViewModel viewModel;
    private ApplicationComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndroidInjection.inject(this);
        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(GreetingViewModel.class);

        viewModel.getLiveData().observe(this,
                this::processResponse);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.common_greeting_button)
    void onCommonGreeingButtonClicked() {
        viewModel.loadCommonGreeting();
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.special_greeting_button)
    void onSpecialGreeingButtonClicked() {
        viewModel.loadSpecialGreeting();
    }

    private void processResponse(String response) {
        textView.setText(response);
    }
}
