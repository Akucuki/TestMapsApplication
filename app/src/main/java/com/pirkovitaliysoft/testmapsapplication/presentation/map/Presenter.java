package com.pirkovitaliysoft.testmapsapplication.presentation.map;

import androidx.lifecycle.MutableLiveData;

import com.pirkovitaliysoft.testmapsapplication.presentation.model.PlaceEntity;

import java.util.List;

public interface Presenter {
    void onCreate();
    void onCannotGrabData();
    void onSuccessfullGrabData(List<PlaceEntity> placeEntities);
    void onDetach();
}
