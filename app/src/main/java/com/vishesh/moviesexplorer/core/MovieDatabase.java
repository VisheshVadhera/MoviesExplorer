package com.vishesh.moviesexplorer.core;

import android.arch.persistence.room.Database;

/**
 * Created by vishesh on 20/6/17.
 */
@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDatabase {

    public abstract MovieDao movieDao();
}
