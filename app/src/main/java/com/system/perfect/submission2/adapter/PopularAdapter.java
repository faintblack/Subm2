package com.system.perfect.submission2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.system.perfect.submission2.model.PopularMovie;
import com.system.perfect.submission2.model.UpcomingMovie;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PopularMovie> movieList;

    public PopularAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<PopularMovie> getMovieList() {
        return movieList;
    }

    public void setMovieList(ArrayList<PopularMovie> movieList) {
        this.movieList = movieList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
