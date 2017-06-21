package com.vishesh.moviesexplorer.favorites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vishesh.moviesexplorer.MainApplication;
import com.vishesh.moviesexplorer.R;
import com.vishesh.moviesexplorer.core.Movie;
import com.vishesh.moviesexplorer.moviedetails.MovieDetailsActivity;
import com.vishesh.moviesexplorer.searchresults.MoviesAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by vishesh on 19/6/17.
 */

public class FavoritesFragment
        extends Fragment
        implements MoviesAdapter.ViewHolder.ClickListener,
        FavoritesPresenter.FavoritesView {

    @BindView(R.id.recycler_view_favorites)
    RecyclerView recyclerViewFavorites;

    private Unbinder unbinder;
    private MoviesAdapter moviesAdapter;

    @Inject
    FavoritesPresenter favoritesPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((MainApplication) getActivity().getApplication()).getInjector().inject(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favoritesPresenter.setView(this);
        favoritesPresenter.initialize();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        favoritesPresenter.onDestroy();
    }

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public void handleRecyclerViewClick(int position) {
        Movie movie = moviesAdapter.getMovies().get(position);
        startActivity(MovieDetailsActivity.createIntent(getActivity(), movie));
    }

    @Override
    public void setFavoriteMovies(List<Movie> movies) {
        moviesAdapter = new MoviesAdapter(getActivity(), movies, this);
        recyclerViewFavorites.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewFavorites.setAdapter(moviesAdapter);
    }
}
