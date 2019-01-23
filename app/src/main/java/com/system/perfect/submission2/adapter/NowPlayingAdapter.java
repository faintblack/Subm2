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
import com.bumptech.glide.request.RequestOptions;
import com.system.perfect.submission2.BuildConfig;
import com.system.perfect.submission2.R;
import com.system.perfect.submission2.model.NowPlayingMovie;

import java.util.ArrayList;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.ViewHolder> {
    private Context context;
    private ArrayList<NowPlayingMovie> movieList;

    public NowPlayingAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<NowPlayingMovie> getMovieList() {
        return movieList;
    }

    public void setMovieList(ArrayList<NowPlayingMovie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.now_playing_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.textTitle.setText(movieList.get(position).getTitle());
        Glide.with(context)
                .load(BuildConfig.TMDB_IMAGE_SMALL + getMovieList().get(position).getPoster_path())
                .apply(new RequestOptions().override(350, 550))
                .into(viewHolder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgPoster;
        private TextView textTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.img_now_playing);
            textTitle = itemView.findViewById(R.id.title_now_playing);
        }
    }
}
