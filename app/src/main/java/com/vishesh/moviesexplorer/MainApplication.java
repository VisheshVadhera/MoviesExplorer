package com.vishesh.moviesexplorer;

import android.app.Application;

import com.vishesh.moviesexplorer.dagger.ApiServiceModule;
import com.vishesh.moviesexplorer.dagger.AppComponent;
import com.vishesh.moviesexplorer.dagger.DaggerAppComponent;
import com.vishesh.moviesexplorer.dagger.DatabaseModule;
import com.vishesh.moviesexplorer.dagger.RetrofitModule;

/**
 * Created by vishesh on 19/6/17.
 */

public class MainApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        setupDagger();
    }

    public AppComponent getInjector() {
        return appComponent;
    }

    private void setupDagger() {
        appComponent = DaggerAppComponent.builder()
                .retrofitModule(new RetrofitModule(getString(R.string.base_url)))
                .apiServiceModule(new ApiServiceModule())
                .databaseModule(new DatabaseModule(getApplicationContext()))
                .build();
    }
}
