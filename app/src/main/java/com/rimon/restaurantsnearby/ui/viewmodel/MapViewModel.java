package com.rimon.restaurantsnearby.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rimon.restaurantsnearby.db.Restaurant;
import com.rimon.restaurantsnearby.network.Resource;
import com.rimon.restaurantsnearby.network.Status;

import java.util.ArrayList;
import java.util.List;

public class MapViewModel extends ViewModel {
    public MutableLiveData<List<MarkerOptions>> mMarkLiveData = new MediatorLiveData<>();
    private LiveData<Resource<List<Restaurant>>> mRestaurants;

    public MapViewModel(RestaurantRepository mRepository) {
        mRestaurants = mRepository.loadData();
    }

    public void onMapReady() {
        mRestaurants.observeForever(this::generateMaker);
    }

    private void generateMaker(Resource<List<Restaurant>> mResource) {
        final List<Restaurant> data = mResource.data;
        if (Status.SUCCESS == mResource.status && data != null) {
            List<MarkerOptions> markerOptions = new ArrayList<>();
            for (Restaurant mRestaurant : data) {
                final LatLng mLatLng = new LatLng(mRestaurant.lat, mRestaurant.lng);
                final MarkerOptions marker = new MarkerOptions().position(mLatLng).title(mRestaurant.name);
                markerOptions.add(marker);
            }
            mMarkLiveData.setValue(markerOptions);
        }
    }
}
