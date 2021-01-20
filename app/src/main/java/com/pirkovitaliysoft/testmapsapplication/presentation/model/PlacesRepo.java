package com.pirkovitaliysoft.testmapsapplication.presentation.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.pirkovitaliysoft.testmapsapplication.presentation.map.Presenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlacesRepo {
    private final String TAG = "Repository";

    private static volatile PlacesRepo instance;
    private final PlacesContainerService service;
    private final Presenter presenter;

    public PlacesRepo(Presenter presenter){
        service = RetrofitService.getService(PlacesContainerService.class);
        this.presenter = presenter;
    }

    public static PlacesRepo getInstance(Presenter presenter){
        PlacesRepo repo = instance;
        if(repo != null){
            return repo;
        }
        synchronized (PlacesRepo.class){
            if(instance == null){
                instance = new PlacesRepo(presenter);
            }
            return instance;
        }
    }

    public MutableLiveData<List<PlaceEntity>> getPlaces(){
        MutableLiveData<List<PlaceEntity>> places = new MutableLiveData<>();
        service.listPlaces().enqueue(new Callback<PlacesContainer>() {
            @Override
            public void onResponse(Call<PlacesContainer> call, Response<PlacesContainer> response) {
                if(response.isSuccessful()){
                    places.setValue(response.body().getPlaces());
                }else {
                    presenter.onCannotGrabData();
                }
            }

            @Override
            public void onFailure(Call<PlacesContainer> call, Throwable t) {
                presenter.onCannotGrabData();
                Log.e(TAG, t.getMessage());
            }
        });
        return places;
    }
}
