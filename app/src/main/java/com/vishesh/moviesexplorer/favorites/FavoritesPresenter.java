package com.vishesh.moviesexplorer.favorites;

import com.vishesh.moviesexplorer.core.Movie;
import com.vishesh.moviesexplorer.core.MovieDatabase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vishesh on 21/6/17.
 */
public class FavoritesPresenter {

    private FavoritesView view;
    private final MovieDatabase movieDatabase;

    @Inject
    public FavoritesPresenter(MovieDatabase movieDatabase) {
        this.movieDatabase = movieDatabase;
    }


    public void setView(FavoritesView view) {
        this.view = view;
    }

    public void initialize() {
        movieDatabase
                .movieDao()
                .getAllMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(@NonNull List<Movie> movies) throws Exception {
                        view.setFavoriteMovies(movies);
                    }
                });
    }

    public interface FavoritesView {

        void setFavoriteMovies(List<Movie> movies);
    }
}
