package com.system.perfect.submission2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.system.perfect.submission2.model.Movie;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    Toolbar tl;
    TextView genre, vote_average, title, original_language, original_title,
            overview, release_date, id, popularity;
    ImageView poster_path, backdrop_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        tl = findViewById(R.id.toolbar);
        setSupportActionBar(tl);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tl = findViewById(R.id.toolbar);
        title = findViewById(R.id.tvDetailJudul);
        release_date = findViewById(R.id.tvDetailRilis);
        vote_average = findViewById(R.id.tvVoteAverage);
        overview = findViewById(R.id.tvDetailSinopsis);
        poster_path = findViewById(R.id.posterSmall);
        backdrop_path = findViewById(R.id.backdrop_image);

        Movie data = getIntent().getParcelableExtra(EXTRA_MOVIE);

        tl.setTitle(data.getTitle());
        title.setText(data.getTitle());
        vote_average.setText(data.getVote_average());
        overview.setText(data.getOverview());
        Glide.with(this).load(BuildConfig.TMDB_IMAGE_SMALL + data.getPoster_path()).into(poster_path);
        Glide.with(this).load(BuildConfig.TMDB_IMAGE_BIG + data.getBackdrop_path()).into(backdrop_path);

        String release = data.getRelease_date();
        SimpleDateFormat formatTanggal = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date tgl = formatTanggal.parse(release);
            SimpleDateFormat formatTglBaru = new SimpleDateFormat("dd MMM yyyy");
            String releaseDate = formatTglBaru.format(tgl);
            release_date.setText(releaseDate);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
