package com.rimon.restaurantsnearby.network;

import androidx.lifecycle.LiveData;

import com.rimon.restaurantsnearby.model.HttpResponse;

import retrofit2.http.GET;

public interface ApiService {
    @GET("venues/explore?client_id=TFX0H3XZW5YEKZLKPZSCYQ0LWYDAV5WBNCBFTA42CQKW4QHT" +
            "&client_secret=3GDGV0ACB54T4FSDCNB0UVRXTJNMA44FMEQHB1OJ4YGWCBYZ" +
            "&v=20180323" +
            "&ll=23.793744,90.404816" +
            "&radius=1000" +
            "&section=food" +
            "&limit=50")
    LiveData<ApiResponse<HttpResponse>> getRestaurant();
}
