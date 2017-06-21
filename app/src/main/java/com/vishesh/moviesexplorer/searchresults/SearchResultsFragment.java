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

import com.vishesh.moviesexplorer.R;
import com.vishesh.moviesexplorer.core.Movie;
import com.vishesh.moviesexplorer.moviedetails.MovieDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SearchResultsFragment
        extends Fragment
        implements MoviesAdapter.ViewHolder.ClickListener {

    @BindView(R.id.recycler_view_results)
    RecyclerView recyclerViewResults;

    private Unbinder unbinder;
    private MoviesAdapter moviesAdapter;

    public static SearchResultsFragment newInstance() {
        return new SearchResultsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_results, container, false);
        unbinder = ButterKnife.bind(this, view);
        initRecyclerAdapter();
        return view;
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
        Movie movie = moviesAdapter.getSearchResults().get(position);
        Intent intent = MovieDetailsActivity.createIntent(getActivity(), movie);
        startActivity(intent);
    }
}