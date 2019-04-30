package com.rimon.restaurantsnearby.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.rimon.restaurantsnearby.network.ApiService;

public class MapViewModel extends ViewModel {
    private final ApiService mApiService;

    public MapViewModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }
}
