package com.vishesh.moviesexplorer.dagger;

import com.vishesh.moviesexplorer.dashboard.DashboardActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vishesh on 19/6/17.
 */
@Singleton
@Component(modules = {RetrofitModule.class, ApiServiceModule.class})
public interface AppComponent {

    void inject(DashboardActivity dashboardActivity);

}
