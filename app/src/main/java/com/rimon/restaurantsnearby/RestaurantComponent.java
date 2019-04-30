package com.rimon.restaurantsnearby;

import com.rimon.restaurantsnearby.base.BaseApplication;
import com.rimon.restaurantsnearby.base.dagger.BaseComponent;

import dagger.Component;

@RestaurantScope
@Component(dependencies = BaseComponent.class, modules = {RestaurantModule.class, ApiModule.class})
public interface RestaurantComponent extends RestaurantGraph {

    void inject(RestaurantApplication application);

    final class Initializer {
        private Initializer() {
        }

        static RestaurantComponent init(BaseApplication app) {
            return DaggerRestaurantComponent.builder()
                    .baseComponent(app.getBaseComponent())
                    .restaurantModule(new RestaurantModule(app))
                    .apiModule(new ApiModule())
                    .build();
        }
    }
}
