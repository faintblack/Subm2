package com.system.perfect.submission2.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.system.perfect.submission2.BuildConfig;
import com.system.perfect.submission2.R;
import com.system.perfect.submission2.model.DetailMovie;
import com.system.perfect.submission2.model.UpcomingMovie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailUpcomingActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    DetailMovie datax ;

    private String id, backdrop_path, budget, homepage, original_language, original_title, overview, popularity, poster_path,
            release_date, tagline, title, vote_average, genre, production_companies, production_countries = "";
    String[] genre_unite, production_companies_unite, production_countries_unite ;

    Toolbar tl;
    TextView txtGenre, txtVote_average, txtTitle, txtOriginal_language, txtOriginal_title,
            txtOverview, txtRelease_date, txtId, txtPopularity;
    ImageView imgPoster_path, imgBackdrop_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_upcoming);

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

        UpcomingMovie data = getIntent().getParcelableExtra(EXTRA_MOVIE);
        String idMovie = data.getId();
        requestMovieData(idMovie);

        //Log.d("cek detailmovie:", data.getOverview());

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

    private void requestMovieData(String idM) {
        String API = BuildConfig.TMDB_API_KEY;
        String url = BuildConfig.TMDB_BASE_URL + "movie/" + idM + "?api_key=" +  API ;

        //RequestQueue initialized
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);

                    id = obj.getString("id");
                    backdrop_path = obj.getString("backdrop_path");
                    budget = obj.getString("budget");

                    JSONArray genres = obj.getJSONArray("genres");
                    genre_unite = new String[genres.length()];
                    for (int i = 0; i < genres.length(); i++){
                        JSONObject g = genres.getJSONObject(i);
                        genre_unite[i] = g.getString("name");
                    }
                    String genre = TextUtils.join(", ", genre_unite);

                    homepage = obj.getString("homepage");
                    original_language = obj.getString("original_language");
                    original_title = obj.getString("original_title");
                    overview = obj.getString("overview");
                    popularity = obj.getString("popularity");
                    poster_path = obj.getString("poster_path");

                    JSONArray pCompanies = obj.getJSONArray("production_companies");
                    production_companies_unite = new String[pCompanies.length()];
                    for (int i = 0; i < pCompanies.length(); i++){
                        JSONObject pCompany = pCompanies.getJSONObject(i);
                        production_companies_unite[i] = pCompany.getString("name");
                    }
                    String production_companies = TextUtils.join(", ", production_companies_unite);

                    JSONArray pCountries = obj.getJSONArray("production_countries");
                    production_countries_unite = new String[pCountries.length()];
                    for (int i = 0; i < pCountries.length(); i++){
                        JSONObject pCountry = pCountries.getJSONObject(i);
                        production_countries_unite[i] = pCountry.getString("name");
                    }
                    String production_countries = TextUtils.join(", ", production_countries_unite);

                    release_date = obj.getString("release_date");
                    tagline = obj.getString("tagline");
                    title = obj.getString("title");
                    vote_average = obj.getString("vote_average");
                    Log.d("cek request :", genre);
                    datax = new DetailMovie(id, backdrop_path, budget, homepage, original_language, original_title, overview, popularity, poster_path,
                            release_date, tagline, title, vote_average, genre, production_companies, production_countries);

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
