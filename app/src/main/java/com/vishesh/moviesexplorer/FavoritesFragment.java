package com.vishesh.moviesexplorer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vishesh.moviesexplorer.dashboard.SearchResultsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by vishesh on 19/6/17.
 */

public class FavoritesFragment extends Fragment {

    @BindView(R.id.recycler_view_favorites)
    RecyclerView recyclerViewFavorites;

    private Unbinder unbinder;
    private SearchResultsAdapter searchResultsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        unbinder = ButterKnife.bind(this, view);
        searchResultsAdapter = new SearchResultsAdapter(getActivity().getApplicationContext());
        recyclerViewFavorites.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerViewFavorites.setAdapter(searchResultsAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }
}
