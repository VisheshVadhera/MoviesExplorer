package com.vishesh.moviesexplorer.dashboard;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vishesh.moviesexplorer.core.Movie;

public class MovieResult implements Parcelable {

    @SerializedName("unit")
    @Expose
    private Integer unit;
    @SerializedName("show_id")
    @Expose
    private Integer showId;
    @SerializedName("show_title")
    @Expose
    private String showTitle;
    @SerializedName("release_year")
    @Expose
    private String releaseYear;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("show_cast")
    @Expose
    private String showCast;
    @SerializedName("director")
    @Expose
    private String director;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("poster")
    @Expose
    private String poster;
    @SerializedName("mediatype")
    @Expose
    private Integer mediatype;
    @SerializedName("runtime")
    @Expose
    private String runtime;

    protected MovieResult(Parcel in) {
        showTitle = in.readString();
        releaseYear = in.readString();
        rating = in.readString();
        category = in.readString();
        showCast = in.readString();
        director = in.readString();
        summary = in.readString();
        poster = in.readString();
        runtime = in.readString();
    }

    public static final Creator<MovieResult> CREATOR = new Creator<MovieResult>() {
        @Override
        public MovieResult createFromParcel(Parcel in) {
            return new MovieResult(in);
        }

        @Override
        public MovieResult[] newArray(int size) {
            return new MovieResult[size];
        }
    };

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getShowCast() {
        return showCast;
    }

    public void setShowCast(String showCast) {
        this.showCast = showCast;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Integer getMediatype() {
        return mediatype;
    }

    public void setMediatype(Integer mediatype) {
        this.mediatype = mediatype;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(showTitle);
        dest.writeString(releaseYear);
        dest.writeString(rating);
        dest.writeString(category);
        dest.writeString(showCast);
        dest.writeString(director);
        dest.writeString(summary);
        dest.writeString(poster);
        dest.writeString(runtime);
    }

    public Movie convertMovieResultToMovie(MovieResult movieResult, boolean isFavorite) {
        Movie movie = new Movie();
        movie.setCategory(movieResult.getCategory());
        movie.setDirector(movieResult.getDirector());
        movie.setMediatype(movieResult.getMediatype());
        movie.setPoster(movieResult.getPoster());
        movie.setRating(movieResult.getRating());
        movie.setReleaseYear(movieResult.getReleaseYear());
        movie.setRuntime(movieResult.getRuntime());
        movie.setShowCast(movieResult.getShowCast());
        movie.setShowId(movieResult.getShowId());
        movie.setShowTitle(movieResult.getShowTitle());
        movie.setSummary(movieResult.getSummary());
        movie.setUnit(movieResult.getUnit());
        movie.setFavorite(isFavorite);
        return movie;
    }
}