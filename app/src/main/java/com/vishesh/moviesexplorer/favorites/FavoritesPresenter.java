package com.vishesh.moviesexplorer.favorites;

import com.vishesh.moviesexplorer.core.Movie;
import com.vishesh.moviesexplorer.core.MovieDatabase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vishesh on 21/6/17.
 */
public class FavoritesPresenter {

    private FavoritesView view;
    private final MovieDatabase movieDatabase;
    private Disposable moviesDisposable;

    @Inject
    FavoritesPresenter(MovieDatabase movieDatabase) {
        this.movieDatabase = movieDatabase;
    }

    public void setView(FavoritesView view) {
        this.view = view;
    }

    void initialize() {
        moviesDisposable = movieDatabase
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

    void onDestroy() {
        moviesDisposable.dispose();
    }

    interface FavoritesView {

        void setFavoriteMovies(List<Movie> movies);
    }
}
