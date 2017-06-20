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
import com.vishesh.moviesexplorer.dashboard.MovieResult;
import com.vishesh.moviesexplorer.moviedetails.MovieDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SearchResultsFragment
        extends Fragment
        implements SearchResultsAdapter.ViewHolder.ClickListener {

    @BindView(R.id.recycler_view_results)
    RecyclerView recyclerViewResults;

    private Unbinder unbinder;
    private SearchResultsAdapter searchResultsAdapter;

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
        searchResultsAdapter = new SearchResultsAdapter(getActivity(), this);
        recyclerViewResults.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewResults.setAdapter(searchResultsAdapter);
    }

    public void addSearchResults(List<MovieResult> movieResults) {
        searchResultsAdapter.addMovieResults(movieResults);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void handleRecyclerViewClick(int position) {
        MovieResult movieResult = searchResultsAdapter.getSearchResults().get(position);
        Intent intent = MovieDetailsActivity.createIntent(getActivity(), movieResult);
        startActivity(intent);
    }
}