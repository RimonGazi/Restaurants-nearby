package com.rimon.restaurantsnearby.ui;

import com.rimon.restaurantsnearby.AppExecutors;
import com.rimon.restaurantsnearby.db.RestaurantDao;
import com.rimon.restaurantsnearby.db.RestaurantDb;
import com.rimon.restaurantsnearby.network.ApiService;
import com.rimon.restaurantsnearby.ui.viewmodel.MapViewModelFactory;
import com.rimon.restaurantsnearby.ui.viewmodel.RestaurantRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class MapModule {

    @Provides
    @MapScope
    RestaurantDao provideRestaurantDa(RestaurantDb mRestaurantDb) {
        return mRestaurantDb.restaurantDao();
    }

    @Provides
    @MapScope
    RestaurantRepository provideRestaurantRepository(ApiService mApiService,
                                                     AppExecutors mAppExecutors,
                                                     RestaurantDao mRestaurantDao) {
        return new RestaurantRepository(mApiService, mAppExecutors, mRestaurantDao);
    }

    @Provides
    @MapScope
    MapViewModelFactory provideMapViewModelFactory(RestaurantRepository mRepository) {
        return new MapViewModelFactory(mRepository);
    }
}
