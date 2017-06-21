package com.vishesh.moviesexplorer.searchresults;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vishesh.moviesexplorer.R;
import com.vishesh.moviesexplorer.core.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private final Context context;
    private final List<Movie> movies;
    private ViewHolder.ClickListener clickListener;

    MoviesAdapter(Context context, ViewHolder.ClickListener clickListener) {
        this.context = context;
        this.movies = new ArrayList<>();
        this.clickListener = clickListener;
    }

    public MoviesAdapter(Context context,
                         List<Movie> movies,
                         ViewHolder.ClickListener clickListener) {
        this.context = context;
        this.movies = movies;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_movie_item, parent, false);
        return new ViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);

        holder.textCategory.setText(movie.getCategory());
        holder.textRuntime.setText(movie.getRuntime());
        holder.textTitle.setText(movie.getShowTitle());
        holder.textYear.setText(movie.getReleaseYear());

        Picasso.with(context)
                .load(movie.getPoster())
                .into(holder.imagePoster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    void addMovieResults(List<Movie> extraMovies) {
        movies.addAll(extraMovies);
        notifyDataSetChanged();
    }

    public List<Movie> getSearchResults() {
        return movies;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ClickListener clickListener;

        @BindView(R.id.text_title)
        TextView textTitle;
        @BindView(R.id.text_year)
        TextView textYear;
        @BindView(R.id.text_category)
        TextView textCategory;
        @BindView(R.id.text_runtime)
        TextView textRuntime;
        @BindView(R.id.image_poster)
        ImageView imagePoster;

        ViewHolder(View itemView, ClickListener clickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.clickListener = clickListener;
        }

        @OnClick(R.id.relative_layout_search_item)
        void onClick() {
            clickListener.handleRecyclerViewClick(this.getLayoutPosition());
        }

        public interface ClickListener {
            void handleRecyclerViewClick(int position);
        }
    }

}
