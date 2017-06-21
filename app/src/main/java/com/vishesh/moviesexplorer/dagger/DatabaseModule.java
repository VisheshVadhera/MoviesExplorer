package com.vishesh.moviesexplorer.dagger;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.vishesh.moviesexplorer.core.MovieDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vishesh on 20/6/17.
 */
@Module
public class DatabaseModule {

    private final Context context;

    public DatabaseModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public MovieDatabase provideMovieDatabase() {
        return Room.databaseBuilder(context, MovieDatabase.class, "moviesDb").build();
    }
}
