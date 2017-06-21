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

    @Query("SELECT * from movies WHERE director = :directorName AND show_title = :title LIMIT 1")
    Movie getMovieByDirector(String directorName, String title);

    @Insert
    void insert(Movie movie);
}
