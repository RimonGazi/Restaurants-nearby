package com.rimon.restaurantsnearby.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MapViewModelFactory implements ViewModelProvider.Factory {
    private final RestaurantRepository mRepository;

    public MapViewModelFactory(RestaurantRepository mRepository) {
        this.mRepository = mRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MapViewModel(mRepository);
    }
}
