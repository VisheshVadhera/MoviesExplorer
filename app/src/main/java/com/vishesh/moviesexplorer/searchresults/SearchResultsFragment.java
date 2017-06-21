package com.vishesh.moviesexplorer.searchresults;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vishesh.moviesexplorer.MainApplication;
import com.vishesh.moviesexplorer.R;
import com.vishesh.moviesexplorer.core.Movie;
import com.vishesh.moviesexplorer.moviedetails.MovieDetailsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SearchResultsFragment
        extends Fragment
        implements MoviesAdapter.ViewHolder.ClickListener,
        SearchResultsPresenter.SearchResultsView {

    @BindView(R.id.recycler_view_results)
    RecyclerView recyclerViewResults;

    private Unbinder unbinder;
    private MoviesAdapter moviesAdapter;

    @Inject
    SearchResultsPresenter searchResultsPresenter;

    public static SearchResultsFragment newInstance() {
        return new SearchResultsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_results, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((MainApplication) getActivity().getApplication()).getInjector().inject(this);
        initRecyclerAdapter();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchResultsPresenter.setView(this);
    }

    private void initRecyclerAdapter() {
        moviesAdapter = new MoviesAdapter(getActivity(), this);
        recyclerViewResults.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewResults.setAdapter(moviesAdapter);
    }

    public void addSearchResults(List<Movie> movies) {
        moviesAdapter.addMovieResults(movies);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void handleRecyclerViewClick(int position) {
        Movie movie = moviesAdapter.getMovies().get(position);
        searchResultsPresenter.onMovieClicked(movie);
    }

    @Override
    public void showMovieDetails(Movie movie) {
        Intent intent = MovieDetailsActivity.createIntent(getActivity(), movie);
        startActivity(intent);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}