package com.pirkovitaliysoft.testmapsapplication.presentation.map;

import com.pirkovitaliysoft.testmapsapplication.presentation.common.ErrorMessages;
import com.pirkovitaliysoft.testmapsapplication.presentation.model.PlaceEntity;

import java.util.List;

public interface MapView {
    String getLoginFromParams();
    void setWindowTitle(String str);
    void showErrorToast(ErrorMessages errorMessage);
    void setAdapterData(List<PlaceEntity> places);
    void enableLoadingBar(boolean enable);
    void enableMap(boolean enable);
    void drawMarkerOnMap(double v, double v1);
    void moveMapCameraToCenter(List<Double> allLat, List<Double> allLng);
}
