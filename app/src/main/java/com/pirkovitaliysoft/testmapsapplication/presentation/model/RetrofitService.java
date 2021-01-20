package com.pirkovitaliysoft.testmapsapplication.presentation.model;

import com.pirkovitaliysoft.testmapsapplication.presentation.common.Config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Config.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static PlacesContainerService getService(Class<PlacesContainerService> serviceClass){
        return retrofit.create(serviceClass);
    }
}
