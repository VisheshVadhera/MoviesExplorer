package com.vishesh.moviesexplorer.moviedetails;

/**
 * Created by vishesh on 20/6/17.
 */
public class MovieDetailsPresenter {

    private MovieDetailsView view;


    public MovieDetailsPresenter() {
    }

    void setView(MovieDetailsView view) {
        this.view = view;
    }

    interface MovieDetailsView {


    }
}
