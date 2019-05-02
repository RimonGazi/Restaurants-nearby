package com.rimon.restaurantsnearby;

import com.rimon.restaurantsnearby.base.dagger.BaseGraph;
import com.rimon.restaurantsnearby.db.RestaurantDb;
import com.rimon.restaurantsnearby.network.ApiService;

import retrofit2.Retrofit;

public interface RestaurantGraph extends BaseGraph {

    Retrofit retrofit();

    ApiService apiService();

    AppExecutors appExecutors();

    RestaurantDb restaurantDb();
}
