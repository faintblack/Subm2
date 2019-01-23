package com.system.perfect.submission2.detail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.system.perfect.submission2.BuildConfig;
import com.system.perfect.submission2.R;
import com.system.perfect.submission2.model.PopularMovie;
import com.system.perfect.submission2.model.UpcomingMovie;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailPopularActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "EXTRA_MOVIE";

    Toolbar tl;
    TextView txtGenre, txtVote_average, txtTitle, txtOriginal_language, txtOriginal_title,
            txtOverview, txtRelease_date, txtId, txtPopularity;
    ImageView imgPoster_path, imgBackdrop_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_popular);
        tl = findViewById(R.id.toolbar_detail_popular);
        setSupportActionBar(tl);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tl = findViewById(R.id.toolbar_detail_popular);
        txtTitle = findViewById(R.id.tvDetailJudul_detail_popular);
        txtRelease_date = findViewById(R.id.tvDetailRilis_detail_popular);
        txtVote_average = findViewById(R.id.tvVoteAverage_detail_popular);
        txtOverview = findViewById(R.id.tvDetailSinopsis_detail_popular);
        imgPoster_path = findViewById(R.id.posterSmall_detail_popular);
        imgBackdrop_path = findViewById(R.id.backdrop_image_detail_popular);

        PopularMovie data = getIntent().getParcelableExtra(EXTRA_MOVIE);
        String idMovie = data.getId();

        tl.setTitle(data.getTitle());
        txtTitle.setText(data.getTitle());
        txtVote_average.setText(data.getGenre());
        Glide.with(this).load(BuildConfig.TMDB_IMAGE_BIG + data.getBackdrop_path()).into(imgBackdrop_path);
        Glide.with(this).load(BuildConfig.TMDB_IMAGE_SMALL + data.getPoster_path()).into(imgPoster_path);
        txtOverview.setText(data.getOverview());

        String release = data.getRelease_date();
        SimpleDateFormat formatTanggal = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date tgl = formatTanggal.parse(release);
            SimpleDateFormat formatTglBaru = new SimpleDateFormat("dd MMM yyyy");
            String releaseDate = formatTglBaru.format(tgl);
            txtRelease_date.setText(releaseDate);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
