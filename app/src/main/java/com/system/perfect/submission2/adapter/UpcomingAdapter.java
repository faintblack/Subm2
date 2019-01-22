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
import com.system.perfect.submission2.model.Movie1;

import java.util.List;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.ViewHolder> {

    private List<Movie1> movieItems;
    private Context context;

    public UpcomingAdapter(List<Movie1> movieItems, Context context) {
        this.movieItems = movieItems;
        this.context = context;
    }
    public UpcomingAdapter(Context context){
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        private ImageView imgPoster;
        private TextView textTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            imgPoster = itemView.findViewById(R.id.img_now_playing);
            textTitle = itemView.findViewById(R.id.title_now_playing);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.upcoming_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.textTitle.setText(movieItems.get(position).getTitle());
        Glide.with(context)
                .load(movieItems.get(position).getPosterPath())
                .apply(new RequestOptions().override(350, 550))
                .into(viewHolder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }


}
