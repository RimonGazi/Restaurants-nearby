package com.rimon.restaurantsnearby.ui;

import com.rimon.restaurantsnearby.network.ApiService;
import com.rimon.restaurantsnearby.ui.viewmodel.MapViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class MapModule {

    @Provides
    @MapScope
    MapViewModelFactory provideMapViewModelFactory(ApiService mApiService) {
        return new MapViewModelFactory(mApiService);
    }
}
