package com.vishesh.moviesexplorer.dagger;

import com.vishesh.moviesexplorer.dashboard.MovieService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by vishesh on 19/6/17.
 */
@Module
public class ApiServiceModule {

    @Provides
    @Singleton
    public MovieService provideMovieService(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }

}
