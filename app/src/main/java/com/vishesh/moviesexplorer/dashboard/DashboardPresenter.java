package com.vishesh.moviesexplorer.dashboard;

import com.vishesh.moviesexplorer.core.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
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
                .map(new Function<List<MovieResult>, List<Movie>>() {
                    @Override
                    public List<Movie> apply(@NonNull List<MovieResult> movieResults) throws Exception {
                        return convertMovieResultsToMovies(movieResults);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Movie>>() {
                    @Override
                    public void onSuccess(@NonNull List<Movie> movies) {
                        //TODO hide loader
                        view.showSearchResult(movies);
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

    private List<Movie> convertMovieResultsToMovies(List<MovieResult> movieResults) {
        List<Movie> movies = new ArrayList<>();
        for (MovieResult movieResult : movieResults) {
            movies.add(movieResult.convertMovieResultToMovie(movieResult, false));
        }
        return movies;
    }

    interface DashboardView {

        void showSearchResult(List<Movie> movieResult);

        void showError(String message);
    }

    public void setView(DashboardView view) {
        this.view = view;
    }
}
