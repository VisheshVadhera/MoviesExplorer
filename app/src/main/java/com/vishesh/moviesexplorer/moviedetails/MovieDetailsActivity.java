package com.vishesh.moviesexplorer.moviedetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.squareup.picasso.Picasso;
import com.vishesh.moviesexplorer.MainApplication;
import com.vishesh.moviesexplorer.R;
import com.vishesh.moviesexplorer.core.Movie;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MovieDetailsActivity
        extends AppCompatActivity
        implements MovieDetailsPresenter.MovieDetailsView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image_movie_poster)
    ImageView imageMoviePoster;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.text_release_year)
    TextView textReleaseYear;
    @BindView(R.id.text_rating)
    TextView textRating;
    @BindView(R.id.text_category)
    TextView textCategory;
    @BindView(R.id.text_cast)
    TextView textCast;
    @BindView(R.id.text_director)
    TextView textDirector;
    @BindView(R.id.text_summary)
    TextView textSummary;
    @BindView(R.id.text_runtime)
    TextView textRuntime;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Inject
    MovieDetailsPresenter movieDetailsPresenter;

    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";

    private Unbinder unbinder;

    public static Intent createIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getApplication()).getInjector().inject(this);
        setContentView(R.layout.activity_movie_details);
        unbinder = ButterKnife.bind(this);
        movieDetailsPresenter.setView(this);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        movieDetailsPresenter.initialize((Movie) getIntent().getParcelableExtra(EXTRA_MOVIE));
    }

    @OnClick(R.id.fab)
    void onClick() {
        movieDetailsPresenter.onFavoriteButtonClicked();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        movieDetailsPresenter.onDestroy();
    }

    @Override
    public void initializeView(Movie movie, boolean disableFavButton) {
        textRuntime.setText(movie.getRuntime());
        textSummary.setText(movie.getSummary());
        textDirector.setText(movie.getDirector());
        textCast.setText(movie.getShowCast());
        textCategory.setText(movie.getCategory());
        textRating.setText(movie.getRating());
        textReleaseYear.setText(movie.getReleaseYear());
        textTitle.setText(movie.getShowTitle());

        Picasso.with(this)
                .load(movie.getPoster())
                .into(imageMoviePoster);

        IconicsDrawable drawable = new IconicsDrawable(this)
                .sizeDp(16);

        if (disableFavButton) {
            fab.setImageDrawable(drawable.icon(GoogleMaterial.Icon.gmd_favorite));
            fab.setEnabled(false);
        } else {
            fab.setImageDrawable(drawable.icon(GoogleMaterial.Icon.gmd_favorite_border));
        }
    }

    @Override
    public void setButtonToFavorite() {
        IconicsDrawable drawable = new IconicsDrawable(this)
                .sizeDp(16)
                .icon(GoogleMaterial.Icon.gmd_favorite);
        fab.setImageDrawable(drawable);
        fab.setEnabled(false);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
