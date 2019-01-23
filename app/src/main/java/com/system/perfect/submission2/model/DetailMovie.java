package com.system.perfect.submission2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DetailMovie implements Parcelable {
    private String id, backdrop_path, budget, homepage, original_language, original_title, overview, popularity, poster_path,
            release_date, tagline, title, vote_average, genre, production_companies, production_countries;

    public DetailMovie(){

    }

    public DetailMovie(String id, String backdrop_path, String budget, String homepage, String original_language,
                       String original_title, String overview, String popularity, String poster_path, String release_date,
                       String tagline, String title, String vote_average, String genre, String production_companies,
                       String production_countries) {
        this.id = id;
        this.backdrop_path = backdrop_path;
        this.budget = budget;
        this.homepage = homepage;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.tagline = tagline;
        this.title = title;
        this.vote_average = vote_average;
        this.genre = genre;
        this.production_companies = production_companies;
        this.production_countries = production_countries;
    }

    public String getId() {
        return id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getBudget() {
        return budget;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTagline() {
        return tagline;
    }

    public String getTitle() {
        return title;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getGenre() {
        return genre;
    }

    public String getProduction_companies() {
        return production_companies;
    }

    public String getProduction_countries() {
        return production_countries;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.backdrop_path);
        dest.writeString(this.budget);
        dest.writeString(this.homepage);
        dest.writeString(this.original_language);
        dest.writeString(this.original_title);
        dest.writeString(this.overview);
        dest.writeString(this.popularity);
        dest.writeString(this.poster_path);
        dest.writeString(this.release_date);
        dest.writeString(this.tagline);
        dest.writeString(this.title);
        dest.writeString(this.vote_average);
        dest.writeString(this.genre);
        dest.writeString(this.production_companies);
        dest.writeString(this.production_countries);
    }

    protected DetailMovie(Parcel in) {
        this.id = in.readString();
        this.backdrop_path = in.readString();
        this.budget = in.readString();
        this.homepage = in.readString();
        this.original_language = in.readString();
        this.original_title = in.readString();
        this.overview = in.readString();
        this.popularity = in.readString();
        this.poster_path = in.readString();
        this.release_date = in.readString();
        this.tagline = in.readString();
        this.title = in.readString();
        this.vote_average = in.readString();
        this.genre = in.readString();
        this.production_companies = in.readString();
        this.production_countries = in.readString();
    }

    public static final Parcelable.Creator<DetailMovie> CREATOR = new Parcelable.Creator<DetailMovie>() {
        @Override
        public DetailMovie createFromParcel(Parcel source) {
            return new DetailMovie(source);
        }

        @Override
        public DetailMovie[] newArray(int size) {
            return new DetailMovie[size];
        }
    };
}
