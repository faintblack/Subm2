package com.system.perfect.submission2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.system.perfect.submission2.BuildConfig;
import com.system.perfect.submission2.DetailMovieActivity;
import com.system.perfect.submission2.MainActivity;
import com.system.perfect.submission2.R;
import com.system.perfect.submission2.adapter.NowPlayingAdapter;
import com.system.perfect.submission2.model.Movie;
import com.system.perfect.submission2.support.ItemClickSupport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NowPlayingFragment extends Fragment {
    private final String TAG = "NowPlayingFragment";

    private RecyclerView rvNowPlaying;
    final ArrayList<Movie> nowPlayingMovies = new ArrayList<>();
    private View v;
    private NowPlayingAdapter mAdapter;

    public NowPlayingFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestMovieData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.now_playing_layout, container, false);
        rvNowPlaying = v.findViewById(R.id.rv_now_playing);
        rvNowPlaying.setHasFixedSize(true);
        showNowPlayingList();
        return v;
    }

    public void showNowPlayingList(){
        rvNowPlaying.setLayoutManager(new GridLayoutManager(getContext(),2));
        mAdapter = new NowPlayingAdapter(getContext());
        mAdapter.setMovieList(nowPlayingMovies);
        rvNowPlaying.setAdapter(mAdapter);

        ItemClickSupport.addTo(rvNowPlaying).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Movie item = nowPlayingMovies.get(position);
                Intent detailMovieIntent = new Intent(getActivity(), DetailMovieActivity.class);
                detailMovieIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, item);
                startActivity(detailMovieIntent);
            }
        });

    }

    private void requestMovieData() {

        String API = BuildConfig.TMDB_API_KEY;
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + API ;

        //RequestQueue initialized
        RequestQueue mRequestQueue = Volley.newRequestQueue(getContext());

        //String Request initialized
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("onResponse :", "Success");
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray data = obj.getJSONArray("results");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject movie = data.getJSONObject(i);
                        Movie item = new Movie(movie);
                        nowPlayingMovies.add(item);
                    }
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
