package com.pirkovitaliysoft.testmapsapplication.presentation.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlacesContainerService {
    @GET("kyiv/places")
    Call<PlacesContainer> listPlaces();
}
