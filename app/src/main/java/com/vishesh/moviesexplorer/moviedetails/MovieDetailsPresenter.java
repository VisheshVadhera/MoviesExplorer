package com.vishesh.moviesexplorer.moviedetails;

import com.vishesh.moviesexplorer.core.Movie;
import com.vishesh.moviesexplorer.core.MovieDao;
import com.vishesh.moviesexplorer.core.MovieDatabase;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vishesh on 20/6/17.
 */
public class MovieDetailsPresenter {

    private MovieDetailsView view;
    private final MovieDatabase movieDatabase;

    @Inject
    public MovieDetailsPresenter(MovieDatabase movieDatabase) {
        this.movieDatabase = movieDatabase;
    }

    void setView(MovieDetailsView view) {
        this.view = view;
    }

    void onFavoriteButtonClicked(Movie movie) {
        if (!movie.isFavorite()) {
            view.setButtonToFavorite();
            movie.setFavorite(true);
            from(movieDatabase.movieDao(), movie)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new CompletableObserver() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onComplete() {
                            view.showMessage("Added to fav!");
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                        }
                    });
        }
    }

    interface MovieDetailsView {

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
