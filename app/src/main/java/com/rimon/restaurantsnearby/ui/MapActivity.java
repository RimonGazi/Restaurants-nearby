package com.rimon.restaurantsnearby.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.rimon.restaurantsnearby.R;
import com.rimon.restaurantsnearby.RestaurantApplication;
import com.rimon.restaurantsnearby.base.BaseActivity;
import com.rimon.restaurantsnearby.base.navigation.FragmentScreenSwitcher;
import com.rimon.restaurantsnearby.base.navigation.HasFragmentContainer;
import com.rimon.restaurantsnearby.ui.fragment.MapFragmentScreen;

import javax.inject.Inject;

public class MapActivity extends BaseActivity implements
        HasFragmentContainer, OnMapReadyCallback {

    @Inject
    FragmentScreenSwitcher mFragmentScreenSwitcher;

    private MapComponent mMapComponent;

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
    protected void destroyComponent() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
    }
}
