package com.vishesh.moviesexplorer.dashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vishesh.moviesexplorer.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vishesh on 20/6/17.
 */

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ViewHolder> {

    private Context context;
    private final List<MovieResult> movieResults;

    public SearchResultsAdapter(Context context) {
        this(context, new ArrayList<MovieResult>());
    }

    SearchResultsAdapter(Context context, List<MovieResult> movieResults) {
        this.context = context;
        this.movieResults = movieResults;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_search_results, parent);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieResult movieResult = movieResults.get(position);

        holder.textCategory.setText(movieResult.getCategory());
        holder.textRuntime.setText(movieResult.getRuntime());
        holder.textTitle.setText(movieResult.getShowTitle());
        holder.textYear.setText(movieResult.getReleaseYear());
    }

    @Override
    public int getItemCount() {
        return movieResults.size();
    }

    void addMovieResult(MovieResult movieResult) {
        movieResults.add(movieResults.size(), movieResult);
        notifyItemInserted(movieResults.size());
    }

    void addMovieResults(List<MovieResult> extraMovieResults) {
        movieResults.addAll(extraMovieResults);
        notifyItemRangeInserted(movieResults.size(), movieResults.size() + extraMovieResults.size());
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_title)
        TextView textTitle;
        @BindView(R.id.text_year)
        TextView textYear;
        @BindView(R.id.text_category)
        TextView textCategory;
        @BindView(R.id.text_runtime)
        TextView textRuntime;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
