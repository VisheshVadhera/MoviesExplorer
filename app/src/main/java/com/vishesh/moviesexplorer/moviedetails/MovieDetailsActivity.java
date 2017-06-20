package com.vishesh.moviesexplorer.moviedetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.squareup.picasso.Picasso;
import com.vishesh.moviesexplorer.MainApplication;
import com.vishesh.moviesexplorer.R;
import com.vishesh.moviesexplorer.dashboard.MovieResult;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MovieDetailsActivity
        extends AppCompatActivity
        implements MovieDetailsPresenter.MovieDetailsView {

    private static final String EXTRA_MOVIE_RESULT = "EXTRA_MOVIE_RESULT";

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

    private MovieResult movieResult;
    private Unbinder unbinder;

    @Inject
    MovieDetailsPresenter movieDetailsPresenter;

    public static Intent createIntent(Context context, MovieResult movieResult) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE_RESULT, movieResult);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        unbinder = ButterKnife.bind(this);
        ((MainApplication) getApplication()).getInjector().inject(this);
        movieDetailsPresenter.setView(this);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        movieResult = getIntent().getParcelableExtra(EXTRA_MOVIE_RESULT);
        initializeViews();
    }

    private void initializeViews() {
        textRuntime.setText(movieResult.getRuntime());
        textSummary.setText(movieResult.getSummary());
        textDirector.setText(movieResult.getDirector());
        textCast.setText(movieResult.getShowCast());
        textCategory.setText(movieResult.getCategory());
        textRating.setText(movieResult.getRating());
        textReleaseYear.setText(movieResult.getReleaseYear());
        textTitle.setText(movieResult.getShowTitle());

        Picasso.with(this)
                .load(movieResult.getPoster())
                .into(imageMoviePoster);

        IconicsDrawable drawable = new IconicsDrawable(this)
                .icon(GoogleMaterial.Icon.gmd_favorite_border)
                .sizeDp(16);
        fab.setImageDrawable(drawable);
    }

    @OnClick(R.id.fab)
    void onClick() {
        IconicsDrawable drawable = new IconicsDrawable(this)
                .icon(GoogleMaterial.Icon.gmd_favorite)
                .sizeDp(16);
        fab.setImageDrawable(drawable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
