package com.system.perfect.submission2.retrofit;

import com.system.perfect.submission2.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientService {

    private static Retrofit retro = null;

    public static Retrofit getClient() {
        if (retro==null) {
            retro = new Retrofit.Builder()
                    .baseUrl(BuildConfig.TMDB_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retro;
    }

}
