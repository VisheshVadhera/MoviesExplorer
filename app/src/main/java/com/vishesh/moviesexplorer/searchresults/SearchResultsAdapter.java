package com.vishesh.moviesexplorer.searchresults;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vishesh.moviesexplorer.R;
import com.vishesh.moviesexplorer.dashboard.MovieResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ViewHolder> {

    private final Context context;
    private final List<MovieResult> movieResults;
    private ViewHolder.ClickListener clickListener;

    public SearchResultsAdapter(Context context, ViewHolder.ClickListener clickListener) {
        this.context = context;
        this.movieResults = new ArrayList<>();
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_search_results, parent, false);
        return new ViewHolder(view, clickListener);
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

    void addMovieResults(List<MovieResult> extraMovieResults) {
        movieResults.addAll(extraMovieResults);
        notifyDataSetChanged();
    }

    public List<MovieResult> getSearchResults() {
        return movieResults;
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
