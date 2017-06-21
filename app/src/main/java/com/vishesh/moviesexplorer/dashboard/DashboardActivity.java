package com.vishesh.moviesexplorer.dashboard;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.vishesh.moviesexplorer.core.Movie;
import com.vishesh.moviesexplorer.favorites.FavoritesFragment;
import com.vishesh.moviesexplorer.MainApplication;
import com.vishesh.moviesexplorer.R;
import com.vishesh.moviesexplorer.searchresults.SearchResultsFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DashboardActivity
        extends AppCompatActivity
        implements DashboardPresenter.DashboardView {

    public static final int SEARCH_RESULTS_FRAGMENT_POSITION = 0;
    public static final int FAVORITES_FRAGMENT_POSITION = 1;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.view_pager_dashboard)
    ViewPager viewPager;

    @Inject
    DashboardPresenter presenter;

    private SearchView searchView;
    private SectionsPagerAdapter sectionsPagerAdapter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        unbinder = ButterKnife.bind(this);
        ((MainApplication) getApplication()).getInjector().inject(this);
        presenter.setView(this);
        setupTabs();
    }

    void setupTabs() {

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        sectionsPagerAdapter.addFragment(SEARCH_RESULTS_FRAGMENT_POSITION, SearchResultsFragment.newInstance());
        sectionsPagerAdapter.addFragment(FAVORITES_FRAGMENT_POSITION, FavoritesFragment.newInstance());

        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);

        tabs.getTabAt(0).setText(getString(R.string.tab_search_results_title));
        tabs.getTabAt(1).setText(getString(R.string.tab_favorites_title));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.searchMovies(query);
                if (searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                menuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showSearchResult(List<Movie> movieResult) {
        SearchResultsFragment searchResultsFragment = (SearchResultsFragment)
                sectionsPagerAdapter.getItem(SEARCH_RESULTS_FRAGMENT_POSITION);
        searchResultsFragment.addSearchResults(movieResult);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
