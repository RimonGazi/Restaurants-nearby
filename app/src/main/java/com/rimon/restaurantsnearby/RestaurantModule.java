package com.rimon.restaurantsnearby;

import android.app.Application;

import androidx.room.Room;

import com.rimon.restaurantsnearby.base.BaseApplication;
import com.rimon.restaurantsnearby.db.RestaurantDb;

import dagger.Module;
import dagger.Provides;

@Module
class RestaurantModule {
    private final BaseApplication application;

    RestaurantModule(BaseApplication application) {
        this.application = application;
    }

    @Provides
    @RestaurantScope
    Application provideAppContext() {
        return this.application;
    }

    @Provides
    @RestaurantScope
    AppExecutors provideAppExecutors() {
        return new AppExecutors();
    }

    @Provides
    @RestaurantScope
    RestaurantDb provideRestaurantDb() {
        return Room.databaseBuilder(application, RestaurantDb.class, RestaurantDb.NAME)
                .build();
    }
}
