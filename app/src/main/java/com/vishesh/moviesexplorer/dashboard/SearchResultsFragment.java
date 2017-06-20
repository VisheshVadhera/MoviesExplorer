package com.vishesh.moviesexplorer.dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vishesh.moviesexplorer.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SearchResultsFragment
        extends Fragment {

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
        searchResultsAdapter = new SearchResultsAdapter(getActivity().getApplicationContext());
        recyclerViewResults.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerViewResults.setAdapter(searchResultsAdapter);
        return view;
    }

    void addSearchResult(MovieResult movieResult) {
        searchResultsAdapter.addMovieResult(movieResult);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}