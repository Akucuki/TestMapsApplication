package com.pirkovitaliysoft.testmapsapplication.presentation.model;

import com.pirkovitaliysoft.testmapsapplication.presentation.common.Config;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlacesContainerService {
    @GET(Config.API_REQUEST)
    Call<PlacesContainer> listPlaces();
}
