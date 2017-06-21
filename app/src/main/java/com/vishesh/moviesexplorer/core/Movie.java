package com.vishesh.moviesexplorer.core;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vishesh on 20/6/17.
 */
@Entity(tableName = "movies")
public class Movie implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "unit")
    private Integer unit;
    @ColumnInfo(name = "show_id")
    private Integer showId;
    @ColumnInfo(name = "show_title")
    private String showTitle;
    @ColumnInfo(name = "release_year")
    private String releaseYear;
    @ColumnInfo(name = "rating")
    private String rating;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "show_cast")
    private String showCast;
    @ColumnInfo(name = "director")
    private String director;
    @ColumnInfo(name = "summary")
    private String summary;
    @ColumnInfo(name = "poster")
    private String poster;
    @ColumnInfo(name = "mediatype")
    private Integer mediatype;
    @ColumnInfo(name = "runtime")
    private String runtime;
    @ColumnInfo(name = "is_favorite")
    private boolean isFavorite;

    public Movie() {

    }

    protected Movie(Parcel in) {
        uid = in.readInt();
        showTitle = in.readString();
        releaseYear = in.readString();
        rating = in.readString();
        category = in.readString();
        showCast = in.readString();
        director = in.readString();
        summary = in.readString();
        poster = in.readString();
        runtime = in.readString();
        isFavorite = in.readByte() != 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeString(showTitle);
        dest.writeString(releaseYear);
        dest.writeString(rating);
        dest.writeString(category);
        dest.writeString(showCast);
        dest.writeString(director);
        dest.writeString(summary);
        dest.writeString(poster);
        dest.writeString(runtime);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
    }
}
