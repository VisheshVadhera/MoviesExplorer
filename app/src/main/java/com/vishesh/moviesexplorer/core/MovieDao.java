package com.vishesh.moviesexplorer.core;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;


/**
 * Created by vishesh on 20/6/17.
 */
@Dao
public interface MovieDao {

    @Query("SELECT * FROM movies")
    Flowable<List<Movie>> getAllMovies();

    @Insert
    void insert(Movie movie);
}
