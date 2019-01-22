package com.system.perfect.submission2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.system.perfect.submission2.model.Movie;
import com.system.perfect.submission2.model.Movie1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    String id, backdrop_path, budget, homepage, original_language, original_title, overview, popularity, poster_path,
            release_date, tagline, title, vote_average;
    List<String> film = new ArrayList<>();

    Toolbar tl;
    TextView txtGenre, txtVote_average, txtTitle, txtOriginal_language, txtOriginal_title,
            txtOverview, txtRelease_date, txtId, txtPopularity;
    ImageView imgPoster_path, imgBackdrop_path;

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
        txtTitle = findViewById(R.id.tvDetailJudul);
        txtRelease_date = findViewById(R.id.tvDetailRilis);
        txtVote_average = findViewById(R.id.tvVoteAverage);
        txtOverview = findViewById(R.id.tvDetailSinopsis);
        imgPoster_path = findViewById(R.id.posterSmall);
        imgBackdrop_path = findViewById(R.id.backdrop_image);

        Movie data = getIntent().getParcelableExtra(EXTRA_MOVIE);
        int idMovie = data.getId();
        //requestMovieData(idMovie);

        tl.setTitle(data.getTitle());
        txtTitle.setText(data.getTitle());
        txtVote_average.setText(data.getVote_average());
        txtOverview.setText(data.getOverview());
        Glide.with(this).load(BuildConfig.TMDB_IMAGE_SMALL + data.getPoster_path()).into(imgPoster_path);
        Glide.with(this).load(BuildConfig.TMDB_IMAGE_BIG + data.getBackdrop_path()).into(imgBackdrop_path);

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

    private void requestMovieData(int id) {

        String API = BuildConfig.TMDB_API_KEY;
        String url = BuildConfig.TMDB_BASE_URL + "movie/" + id + "?api_key=" +  API ;

        //RequestQueue initialized
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    tl.setTitle(obj.getString("title"));

                    String id = obj.getString("id");
                    String backdrop_path = obj.getString("backdrop_path");
                    String budget = obj.getString("budget");
                    List<String> genre = new ArrayList<>();
                        JSONArray genres = obj.getJSONArray("genres");
                        for (int i = 0; i < genres.length(); i++){
                            JSONObject g = genres.getJSONObject(i);
                            genre.add(g.getString("name"));
                        }
                    String homepage = obj.getString("homepage");
                    String original_language = obj.getString("original_language");
                    String original_title = obj.getString("original_title");

                    txtOverview.setText(obj.getString("overview"));

                    String popularity = obj.getString("popularity");
                    String poster_path = obj.getString("poster_path");
                    List<String> production_companies = new ArrayList<>();
                        JSONArray pCompanies = obj.getJSONArray("production_companies");
                        for (int i = 0; i < pCompanies.length(); i++){
                            JSONObject pCompany = pCompanies.getJSONObject(i);
                            production_companies.add(pCompany.getString("name"));
                        }
                    List<String> production_countries = new ArrayList<>();
                        JSONArray pCountries = obj.getJSONArray("production_countries");
                        for (int i = 0; i < pCountries.length(); i++){
                            JSONObject pCountry = pCountries.getJSONObject(i);
                            production_countries.add(pCountry.getString("name"));
                        }
                    String release_date = obj.getString("release_date");
                    String tagline = obj.getString("tagline");
                    txtTitle.setText(obj.getString("title"));
                    txtVote_average.setText(obj.getString("vote_average"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("onResponse :", "Failed");
            }
        });
        mRequestQueue.add(mStringRequest);
    }
}
