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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.system.perfect.submission2.BuildConfig;
import com.system.perfect.submission2.DetailMovieActivity;
import com.system.perfect.submission2.R;
import com.system.perfect.submission2.adapter.UpcomingAdapter;
import com.system.perfect.submission2.model.UpcomingMovie;
import com.system.perfect.submission2.support.ItemClickSupport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UpcomingFragment extends Fragment {
    private final String TAG = "UpcomingFragment";

    private RecyclerView rvUpcoming;
    final ArrayList<UpcomingMovie> upcomingMovies = new ArrayList<>();
    private View v;

    public UpcomingFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestMovieData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.upcoming_layout, container, false);
        rvUpcoming = v.findViewById(R.id.rv_upcoming);
        rvUpcoming.setHasFixedSize(true);
        showUpcomingList();
        return v;
    }

    public void showUpcomingList(){
        rvUpcoming.setLayoutManager(new GridLayoutManager(getContext(),2));
        UpcomingAdapter mAdapter = new UpcomingAdapter(getContext());
        mAdapter.setMovieList(upcomingMovies);
        rvUpcoming.setAdapter(mAdapter);
        //ganti
        ItemClickSupport.addTo(rvUpcoming).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                UpcomingMovie item = upcomingMovies.get(position);
                Intent detailMovieIntent = new Intent(getActivity(), DetailMovieActivity.class);
                detailMovieIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, item);
                startActivity(detailMovieIntent);
            }
        });

    }

    private void requestMovieData() {

        String API = BuildConfig.TMDB_API_KEY;
        String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=" + API + "&language=en-US";

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
                        UpcomingMovie item = new UpcomingMovie(movie);
                        upcomingMovies.add(item);
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
