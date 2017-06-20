package com.vishesh.moviesexplorer.core;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


/**
 * Created by vishesh on 20/6/17.
 */
@Dao
public interface MovieDao {

    @Query("SELECT * FROM user")
    List<Movie> getAllMovies();

    @Insert
    void insert(Movie movie);
}
