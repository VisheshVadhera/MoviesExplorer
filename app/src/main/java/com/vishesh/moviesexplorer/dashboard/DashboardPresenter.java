package com.vishesh.moviesexplorer.dashboard;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

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

        movieService.searchMovies(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<MovieResult>() {
                    @Override
                    public void onSuccess(@NonNull MovieResult movieResult) {
                        //TODO hide loader
                        view.showSearchResult(movieResult);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //TODO hide loader
                    }
                });
    }

    interface DashboardView {

        void showSearchResult(MovieResult movieResult);
    }

    public void setView(DashboardView view) {
        this.view = view;
    }
}
