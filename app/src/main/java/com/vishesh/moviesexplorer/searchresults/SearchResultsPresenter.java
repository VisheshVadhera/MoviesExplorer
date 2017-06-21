package com.vishesh.moviesexplorer.searchresults;

import com.vishesh.moviesexplorer.core.Movie;
import com.vishesh.moviesexplorer.core.MovieDatabase;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class SearchResultsPresenter {

    private final MovieDatabase movieDatabase;
    private SearchResultsView view;
    private DisposableSingleObserver<Movie> disposableSingleObserver;

    @Inject
    SearchResultsPresenter(MovieDatabase movieDatabase) {
        this.movieDatabase = movieDatabase;
    }

    void onMovieClicked(final Movie movie) {
        disposableSingleObserver = from(movie).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Movie>() {
                    @Override
                    public void onSuccess(@NonNull Movie movie) {
                        view.showMovieDetails(movie);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showMovieDetails(movie);
                    }
                });
    }

    void onDestroy() {
        if (disposableSingleObserver != null) {
            disposableSingleObserver.dispose();
        }
        view = null;
    }

    interface SearchResultsView {

        void showMovieDetails(Movie movie);
    }

    public void setView(SearchResultsView view) {
        this.view = view;
    }

    public Single<Movie> from(final Movie movie) {
        return Single.create(new SingleOnSubscribe<Movie>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<Movie> emitter) throws Exception {
                try {
                    Movie movieByDirector = movieDatabase.movieDao().getMovieByDirector(movie.getDirector(), movie.getShowTitle());
                    if (!emitter.isDisposed()) {
                        emitter.onSuccess(movieByDirector);
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
