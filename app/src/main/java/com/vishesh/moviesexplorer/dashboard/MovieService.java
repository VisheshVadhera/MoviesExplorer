package com.vishesh.moviesexplorer.dashboard;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vishesh on 19/6/17.
 */

public interface MovieService {

    @GET("api.php")
    Single<List<MovieResult>> searchMoviesByDirector(@Query("director") String title);

}
