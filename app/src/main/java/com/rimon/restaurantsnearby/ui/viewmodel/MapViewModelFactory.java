package com.rimon.restaurantsnearby.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.rimon.restaurantsnearby.network.ApiService;

public class MapViewModelFactory implements ViewModelProvider.Factory {
    private final ApiService mApiService;

    public MapViewModelFactory(final ApiService mApiService) {
        this.mApiService = mApiService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MapViewModel(mApiService);
    }
}
