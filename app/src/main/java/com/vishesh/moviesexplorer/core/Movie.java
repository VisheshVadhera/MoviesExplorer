package com.vishesh.moviesexplorer.core;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by vishesh on 20/6/17.
 */
@Entity
public class Movie {

    @PrimaryKey
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
}
