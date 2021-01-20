package com.pirkovitaliysoft.testmapsapplication.presentation.map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pirkovitaliysoft.testmapsapplication.R;
import com.pirkovitaliysoft.testmapsapplication.databinding.ActivityMapBinding;
import com.pirkovitaliysoft.testmapsapplication.presentation.common.Config;
import com.pirkovitaliysoft.testmapsapplication.presentation.common.ErrorMessages;
import com.pirkovitaliysoft.testmapsapplication.presentation.map.recycler.RecyclerViewAdapter;
import com.pirkovitaliysoft.testmapsapplication.presentation.model.PlaceEntity;
import com.pirkovitaliysoft.testmapsapplication.presentation.model.PlacesViewModel;

import java.util.List;

public class MapActivity extends AppCompatActivity implements MapView{
    private Presenter presenter;

    private ActivityMapBinding binding;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerViewAdapter = new RecyclerViewAdapter();
        binding.recycler.setAdapter(recyclerViewAdapter);

        presenter = new MapPresenter(this);
        presenter.onCreate();

        PlacesViewModel viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())
                .create(PlacesViewModel.class);
        viewModel.init(presenter);
        viewModel.getAllPlaces().observe(this,
                placeEntities -> presenter.onSuccessfullGrabData(placeEntities));

        binding.map.onCreate(savedInstanceState);
    }

    @Override
    public String getLoginFromParams() {
        Intent intent = getIntent();
        return intent.getStringExtra("login");
    }

    @Override
    public void setWindowTitle(String str) {
        setTitle(str);
    }

    @Override
    public void showErrorToast(ErrorMessages errorMessage) {
        String message = "";
        final Resources res = getResources();
        switch (errorMessage){
            case NO_INTERNET:
                message = res.getString(R.string.error_no_internet);
                break;
            case EMPTY_FIELDS:
                message = res.getString(R.string.error_empty_fields);
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setAdapterData(List<PlaceEntity> places) {
        recyclerViewAdapter.setPlaces(places);
    }

    @Override
    public void enableLoadingBar(boolean enable) {
        LottieAnimationView loadingBar = binding.loadingBar;
        if(enable){
            loadingBar.setVisibility(View.VISIBLE);
        }else{
            loadingBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void enableMap(boolean enable) {
        com.google.android.gms.maps.MapView mapView = binding.map;
        if(enable){
            mapView.setVisibility(View.VISIBLE);
        }else{
            mapView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void drawMarkerOnMap(double v, double v1) {
        LatLng latLng = new LatLng(v, v1);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .draggable(false)
                .visible(true);
        binding.map.getMapAsync(googleMap -> googleMap.addMarker(markerOptions));
    }

    @Override
    public void moveMapCameraToCenter(List<Double> allLat, List<Double> allLng) {
        LatLngBounds.Builder latLngBuilder = new LatLngBounds.Builder();
        for(int i = 0; i < allLat.size(); i++){
            latLngBuilder.include(new LatLng(allLat.get(i), allLng.get(i)));
        }
        binding.map.getMapAsync(googleMap -> {
           googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngBuilder.build().getCenter(),
                   Config.MAP_ZOOM));
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
        binding.map.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.map.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.map.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        binding.map.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        binding.map.onLowMemory();
    }
}