package com.rimon.restaurantsnearby.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.rimon.restaurantsnearby.R;
import com.rimon.restaurantsnearby.RestaurantApplication;
import com.rimon.restaurantsnearby.base.BaseActivity;
import com.rimon.restaurantsnearby.base.navigation.FragmentScreenSwitcher;
import com.rimon.restaurantsnearby.base.navigation.HasFragmentContainer;
import com.rimon.restaurantsnearby.databinding.ActivityMapBinding;
import com.rimon.restaurantsnearby.ui.fragment.MapFragmentScreen;
import com.rimon.restaurantsnearby.ui.viewmodel.MapViewModel;
import com.rimon.restaurantsnearby.ui.viewmodel.MapViewModelFactory;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

public class MapActivity extends BaseActivity implements
        HasFragmentContainer, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    @Inject
    FragmentScreenSwitcher mFragmentScreenSwitcher;

    @Inject
    MapViewModelFactory mMapViewModelFactory;

    private MapComponent mMapComponent;
    private MapViewModel mapViewModel;
    private GoogleMap mGoogleMap;
    private ActivityMapBinding mBinding;
    private BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_map;
    }

    @Override
    protected void onCreateComponent() {
        final RestaurantApplication mApplication = RestaurantApplication.get(this);
        mMapComponent = DaggerMapComponent.builder()
                .restaurantComponent(mApplication.getAppComponent())
                .mapModule(new MapModule())
                .build();
        mMapComponent.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = (ActivityMapBinding) getViewDataBinding();
        bottomSheetBehavior = BottomSheetBehavior.from(mBinding.bottomSheet);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                updateExpandIconState(newState);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
    }

    @Override
    protected boolean haveToolbar() {
        return true;
    }

    @Override
    protected int resToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected void initToolbarForChildActivity(ActionBar mActionBar, Toolbar mToolbar) {
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.fragment_container;
    }

    @Override
    public FragmentManager getManager() {
        return getSupportFragmentManager();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapViewModel = ViewModelProviders.of(this, mMapViewModelFactory).get(MapViewModel.class);
        mapViewModel.mMarkLiveData.observe(this, this::setupMarker);
        mFragmentScreenSwitcher.attach(this);
        if (!mFragmentScreenSwitcher.hasFragments()) {
            final MapFragmentScreen mFragmentScreen = new MapFragmentScreen();
            final SupportMapFragment mMapFragment = mFragmentScreen.getFragment();
            mMapFragment.getMapAsync(this);
            mFragmentScreenSwitcher.openWithClearStack(mFragmentScreen);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFragmentScreenSwitcher.detach();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setOnMarkerClickListener(this);
        mapViewModel.onMapReady();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        mBinding.markerTitle.setText(marker.getTitle());
        mBinding.markerDescription.setText(getString(R.string.marker_description,
                marker.getPosition().latitude, marker.getPosition().longitude));
        return false;
    }

    @Override
    protected void destroyComponent() {
        mMapComponent = null;
    }

    private void updateExpandIconState(int newState) {
        float rotation = 0;
        if (BottomSheetBehavior.STATE_EXPANDED == newState) {
            rotation = 0;
        } else if (BottomSheetBehavior.STATE_COLLAPSED == newState) {
            rotation = 180f;
        } else if (BottomSheetBehavior.STATE_HIDDEN == newState) {
            rotation = 180f;
        }
        mBinding.expandIcon.animate().rotationX(rotation).start();
    }

    private void setupMarker(List<MarkerOptions> markerOptions) {
        mGoogleMap.clear();
        if (!markerOptions.isEmpty()) {
            final int size = markerOptions.size();
            final Random mRandom = new Random();
            final int mRandomValue = mRandom.nextInt(size);
            for (int i = 0; i < size; i++) {
                final MarkerOptions markerOption = markerOptions.get(i);
                final Marker marker = mGoogleMap.addMarker(markerOption);
                if (mRandomValue == i) {
                    marker.showInfoWindow();
                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 18));
                    onMarkerClick(marker);
                }
            }
        }
    }
}
