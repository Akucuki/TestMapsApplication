package com.pirkovitaliysoft.testmapsapplication.map;

import com.pirkovitaliysoft.testmapsapplication.model.PlaceEntity;

import java.util.List;

public interface Presenter {
    void onCreate();
    void onCannotGrabData();
    void onSuccessfullGrabData(List<PlaceEntity> placeEntities);
    void onDetach();
}
