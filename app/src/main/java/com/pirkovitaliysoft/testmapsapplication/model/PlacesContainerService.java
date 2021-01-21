package com.pirkovitaliysoft.testmapsapplication.model;

import com.pirkovitaliysoft.testmapsapplication.common.Config;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlacesContainerService {
    @GET(Config.API_REQUEST)
    Call<PlacesContainer> listPlaces();
}
