package com.system.perfect.submission2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.system.perfect.submission2.BuildConfig;
import com.system.perfect.submission2.R;
import com.system.perfect.submission2.model.PopularMovie;
import com.system.perfect.submission2.model.UpcomingMovie;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        private ImageView imgPoster;
        private TextView textTitle, textDescription, textRelease;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.img_all_movies);
            textTitle = itemView.findViewById(R.id.title_all_movies);
            textDescription = itemView.findViewById(R.id.description_all_movies);
            textRelease = itemView.findViewById(R.id.release_all_movies);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.all_movies_item, viewGroup, false);
        return new PopularAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.textTitle.setText(movieList.get(position).getTitle());
        viewHolder.textDescription.setText(movieList.get(position).getOverview());
        //viewHolder.textRelease.setText(movieList.get(position).getRelease_date());
        Glide.with(context).load(BuildConfig.TMDB_IMAGE_SMALL + movieList.get(position).getPoster_path()).into(viewHolder.imgPoster);

        String release = movieList.get(position).getRelease_date();
        SimpleDateFormat formatTanggal = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date tgl = formatTanggal.parse(release);
            SimpleDateFormat formatTglBaru = new SimpleDateFormat("dd MMM yyyy");
            String releaseDate = formatTglBaru.format(tgl);
            viewHolder.textRelease.setText(releaseDate);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


}
