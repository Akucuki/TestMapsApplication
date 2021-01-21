package com.pirkovitaliysoft.testmapsapplication.map;

import com.pirkovitaliysoft.testmapsapplication.common.ErrorMessages;
import com.pirkovitaliysoft.testmapsapplication.model.PlaceEntity;

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
