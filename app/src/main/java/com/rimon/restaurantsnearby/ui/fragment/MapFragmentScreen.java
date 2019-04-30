package com.rimon.restaurantsnearby.ui.fragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.SupportMapFragment;
import com.rimon.restaurantsnearby.base.navigation.FragmentScreen;

public class MapFragmentScreen extends FragmentScreen {
    @Override
    protected Fragment createFragment() {
        return SupportMapFragment.newInstance();
    }

    @Nullable
    @Override
    public SupportMapFragment getFragment() {
        return (SupportMapFragment) super.getFragment();
    }

    @Override
    protected String getName() {
        return getClass().getName();
    }
}
