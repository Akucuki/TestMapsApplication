package com.pirkovitaliysoft.testmapsapplication.presentation.map;

import androidx.annotation.Nullable;

import com.pirkovitaliysoft.testmapsapplication.presentation.common.ErrorMessages;
import com.pirkovitaliysoft.testmapsapplication.presentation.model.PlaceEntity;

import java.util.ArrayList;
import java.util.List;

public class MapPresenter implements Presenter{
    @Nullable
    private MapView view;

    public MapPresenter(@Nullable MapView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
        view.setWindowTitle(view.getLoginFromParams());
    }

    public void onCannotGrabData(){
        view.showErrorToast(ErrorMessages.NO_INTERNET);
    }

    @Override
    public void onSuccessfullGrabData(List<PlaceEntity> placeEntities) {
        if(placeEntities.size() == 0){
            view.showErrorToast(ErrorMessages.NO_INTERNET);
            return;
        }
        view.setAdapterData(placeEntities);
        view.enableLoadingBar(false);
        onMapReady(placeEntities);
        view.enableMap(true);
    }

    public void onMapReady(List<PlaceEntity> placeEntities){
        List<Double> allLat = new ArrayList<>();
        List<Double> allLng = new ArrayList<>();

        for (PlaceEntity placeEntity : placeEntities) {
            double currentLat = placeEntity.getLat();
            double currentLng = placeEntity.getLng();
            view.drawMarkerOnMap(currentLat, currentLng);

            allLat.add(currentLat);
            allLng.add(currentLng);
        }

        view.moveMapCameraToCenter(allLat, allLng);
    }

    @Override
    public void onDetach(){
        view = null;
    }
}
