package com.vishesh.moviesexplorer.core;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by vishesh on 20/6/17.
 */
@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase{

    public abstract MovieDao movieDao();
}
