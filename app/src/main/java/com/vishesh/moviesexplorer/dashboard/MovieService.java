package com.vishesh.moviesexplorer.dashboard;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vishesh on 19/6/17.
 */

public interface MovieService {

    @GET("api.php")
    Single<MovieResult> searchMovies(@Query("title") String title);

}
