package com.vishesh.moviesexplorer.moviedetails;

import com.vishesh.moviesexplorer.core.Movie;
import com.vishesh.moviesexplorer.core.MovieDao;
import com.vishesh.moviesexplorer.core.MovieDatabase;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MovieDetailsPresenter {

    private MovieDetailsView view;
    private final MovieDatabase movieDatabase;
    private Movie movie;
    private Disposable disposable;

    @Inject
    MovieDetailsPresenter(MovieDatabase movieDatabase) {
        this.movieDatabase = movieDatabase;
    }

    void setView(MovieDetailsView view) {
        this.view = view;
    }

    void onFavoriteButtonClicked() {
        if (!movie.isFavorite()) {
            view.setButtonToFavorite();
            movie.setFavorite(true);
            disposable = from(movieDatabase.movieDao(), movie)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action() {
                        @Override
                        public void run() throws Exception {
                            //No op
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            view.showMessage("Unable to perform operation");
                        }
                    });
        } else {
            view.showMessage("Movie has already been favourited!");
        }
    }

    void initialize(Movie movie) {
        this.movie = movie;
        view.initializeView(movie, movie.isFavorite());
    }

    void onDestroy() {
        if (disposable != null) {
            disposable.dispose();
        }
        view = null;
    }

    interface MovieDetailsView {

        void initializeView(Movie movie, boolean disableFavButton);

        void setButtonToFavorite();

        void showMessage(String s);
    }

    private Completable from(final MovieDao movieDao, final Movie movie) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {
                try {
                    movieDao.insert(movie);
                    if (!emitter.isDisposed()) {
                        emitter.onComplete();
                    }
                } catch (Exception e) {
                    Exceptions.throwIfFatal(e);
                    if (!emitter.isDisposed()) {
                        emitter.onError(e);
                    }
                }
            }
        });
    }
}
