package com.pirkovitaliysoft.testmapsapplication.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pirkovitaliysoft.testmapsapplication.map.Presenter;

import java.util.List;

public class PlacesViewModel extends ViewModel {
    private MutableLiveData<List<PlaceEntity>> placesLiveData;
    private PlacesRepo placesRepo;

    public PlacesViewModel(){
    }

    public void init(Presenter presenter){
        if(placesLiveData != null){
            return;
        }
        placesRepo = PlacesRepo.getInstance(presenter);
        placesLiveData = placesRepo.getPlaces();
    }

    public MutableLiveData<List<PlaceEntity>> getAllPlaces(){
        return placesLiveData;
    }
}
