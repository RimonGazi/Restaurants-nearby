package com.rimon.restaurantsnearby.ui;

import com.rimon.restaurantsnearby.RestaurantComponent;

import dagger.Component;

@MapScope
@Component(dependencies = RestaurantComponent.class, modules = {MapModule.class})
public interface MapComponent extends MapGraph {
    void inject(MapActivity mActivity);
}


