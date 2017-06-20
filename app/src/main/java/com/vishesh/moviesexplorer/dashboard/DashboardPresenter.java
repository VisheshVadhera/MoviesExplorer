package com.vishesh.moviesexplorer.dashboard;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by vishesh on 19/6/17.
 */

class DashboardPresenter {

    private MovieService movieService;
    private DashboardView view;

    @Inject
    public DashboardPresenter(MovieService movieService) {
        this.movieService = movieService;
    }

    void searchMovies(String query) {
        //TODO show loader.

        movieService.searchMoviesByDirector(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<MovieResult>>() {
                    @Override
                    public void onSuccess(@NonNull List<MovieResult> movieResult) {
                        //TODO hide loader
                        view.showSearchResult(movieResult);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            if (httpException.code() == 404) {
                               view.showError(httpException.message());
                            }
                        }
                    }
                });
    }

    interface DashboardView {

        void showSearchResult(List<MovieResult> movieResult);

        void showError(String message);
    }

    public void setView(DashboardView view) {
        this.view = view;
    }
}
