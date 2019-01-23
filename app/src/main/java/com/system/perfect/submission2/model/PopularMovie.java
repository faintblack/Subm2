package com.system.perfect.submission2.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PopularMovie implements Parcelable {
    private String id, popularity, genre, vote_average, title, poster_path, original_language, original_title,
            backdrop_path, overview, release_date;


    public PopularMovie(JSONObject obj){
        try{
            // Get data from JSON
            String id = obj.getString("id");
            String popularity = obj.getString("popularity");
            String title = obj.getString("title");
            String rating = obj.getString("vote_average");
            String poster = obj.getString("poster_path");
            String backdrop = obj.getString("backdrop_path");
            JSONArray genre_ids = obj.getJSONArray("genre_ids");
            ArrayList<String> gen = new ArrayList<String>();
            String genre = gen.toString();
            String original_language = obj.getString("original_language");
            String original_title = obj.getString("original_title");
            String release_date = obj.getString("release_date");
            String synopsys = obj.getString("overview");

            // Set data to attribute
            setId(id);
            setPopularity(popularity);
            setTitle(title);
            setPoster_path(poster);
            setBackdrop_path(backdrop);
            setOriginal_language(original_language);
            setOriginal_title(original_title);
            setVote_average(rating);setRelease_date(release_date);
            setOverview(synopsys);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.popularity);
        dest.writeString(this.genre);
        dest.writeString(this.vote_average);
        dest.writeString(this.title);
        dest.writeString(this.poster_path);
        dest.writeString(this.original_language);
        dest.writeString(this.original_title);
        dest.writeString(this.backdrop_path);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
    }

    protected PopularMovie(Parcel in) {
        this.id = in.readString();
        this.popularity = in.readString();
        this.genre = in.readString();
        this.vote_average = in.readString();
        this.title = in.readString();
        this.poster_path = in.readString();
        this.original_language = in.readString();
        this.original_title = in.readString();
        this.backdrop_path = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
    }

    public static final Parcelable.Creator<PopularMovie> CREATOR = new Parcelable.Creator<PopularMovie>() {
        @Override
        public PopularMovie createFromParcel(Parcel source) {
            return new PopularMovie(source);
        }

        @Override
        public PopularMovie[] newArray(int size) {
            return new PopularMovie[size];
        }
    };
}
