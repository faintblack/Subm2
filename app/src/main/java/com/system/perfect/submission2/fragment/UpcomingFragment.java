package com.system.perfect.submission2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.system.perfect.submission2.BuildConfig;
import com.system.perfect.submission2.R;
import com.system.perfect.submission2.adapter.NowPlayingAdapter;
import com.system.perfect.submission2.adapter.UpcomingAdapter;
import com.system.perfect.submission2.model.Movie1;
import com.system.perfect.submission2.retrofit.ClientService;
import com.system.perfect.submission2.retrofit.DataInterface;
import com.system.perfect.submission2.retrofit.RequestResponse;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingFragment extends Fragment {

    private RecyclerView rvUpcoming;
    private View v;
    private UpcomingAdapter mAdapter ;
    List<Movie1> movieItem;

    public UpcomingFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.upcoming_layout, container, false);
        rvUpcoming = v.findViewById(R.id.rv_upcoming);
        rvUpcoming.setHasFixedSize(true);
        rvUpcoming.setLayoutManager(new GridLayoutManager(getContext(),2));
        rvUpcoming.setAdapter(mAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataInterface service = ClientService.getClient().create(DataInterface.class);
        retrofit2.Call<RequestResponse> call = service.getUpcoming(BuildConfig.TMDB_API_KEY);
        call.enqueue(new Callback<RequestResponse>() {
            @Override
            public void onResponse(retrofit2.Call<RequestResponse> call, Response<RequestResponse> response) {
                movieItem = response.body().getResults();
                generate(movieItem);
            }

            @Override
            public void onFailure(retrofit2.Call<RequestResponse> call, Throwable t) {
                Log.d("Retrofit data", "Gagal");
            }
        });
    }

    private void generate(List<Movie1> movieItem){
        mAdapter = new UpcomingAdapter(movieItem, getContext());
    }



}
