package com.rimon.restaurantsnearby.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.rimon.restaurantsnearby.AppExecutors;
import com.rimon.restaurantsnearby.db.Restaurant;
import com.rimon.restaurantsnearby.db.RestaurantDao;
import com.rimon.restaurantsnearby.model.Category;
import com.rimon.restaurantsnearby.model.Group;
import com.rimon.restaurantsnearby.model.HttpResponse;
import com.rimon.restaurantsnearby.model.Item;
import com.rimon.restaurantsnearby.model.Response;
import com.rimon.restaurantsnearby.model.Venue;
import com.rimon.restaurantsnearby.network.ApiResponse;
import com.rimon.restaurantsnearby.network.ApiService;
import com.rimon.restaurantsnearby.network.NetworkBoundResource;
import com.rimon.restaurantsnearby.network.Resource;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository {
    private final ApiService mApiService;
    private final AppExecutors mAppExecutors;
    private final RestaurantDao mRestaurantDao;

    public RestaurantRepository(ApiService mApiService,
                                AppExecutors mAppExecutors,
                                RestaurantDao mRestaurantDao) {
        this.mApiService = mApiService;
        this.mAppExecutors = mAppExecutors;
        this.mRestaurantDao = mRestaurantDao;
    }

    public LiveData<Resource<List<Restaurant>>> loadData() {
        return new NetworkBoundResource<List<Restaurant>, HttpResponse>(mAppExecutors) {
            @Override
            protected void saveCallResult(@NonNull HttpResponse mData) {
                saveDatabase(mData);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Restaurant> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Restaurant>> loadFromDb() {
                return mRestaurantDao.loadAll();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<HttpResponse>> createCall() {
                return mApiService.getRestaurant();
            }
        }.asLiveData();
    }

    private void saveDatabase(@NonNull HttpResponse mData) {
        final Response response = mData.getResponse();
        if (response != null) {
            final List<Group> groups = response.getGroups();
            if (groups != null && groups.size() > 0) {
                final Group group = groups.get(0);
                final List<Item> items = group.getItems();
                final List<Restaurant> mRestaurants = new ArrayList<>();
                for (Item item : items) {
                    Restaurant mRestaurant = new Restaurant();
                    final Venue venue = item.getVenue();
                    mRestaurant.id = venue.getId();
                    mRestaurant.lat = venue.getLocation().getLat();
                    mRestaurant.lng = venue.getLocation().getLng();
                    final List<Category> categories = venue.getCategories();
                    if (categories != null && categories.size() > 0) {
                        final Category category = categories.get(0);
                        mRestaurant.name = category.getName();
                        mRestaurant.iconUlr = category.getIcon().getPrefix() + category.getIcon().getSuffix();
                    } else {
                        mRestaurant.name = venue.getName();
                    }
                    mRestaurants.add(mRestaurant);
                }
                if (!mRestaurants.isEmpty()) {
                    mRestaurantDao.insertAll(mRestaurants);
                }
            }
        }
    }
}
