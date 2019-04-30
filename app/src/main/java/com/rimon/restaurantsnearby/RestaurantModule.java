package com.rimon.restaurantsnearby;

import android.app.Application;

import com.rimon.restaurantsnearby.base.BaseApplication;

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
}
