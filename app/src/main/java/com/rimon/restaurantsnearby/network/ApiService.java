package com.rimon.restaurantsnearby.network;

import com.rimon.restaurantsnearby.model.HttpResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    //  https://api.foursquare.com/v2/venues/explore?
    //   client_id=TFX0H3XZW5YEKZLKPZSCYQ0LWYDAV5WBNCBFTA42CQKW4QHT
    //   &client_secret=3GDGV0ACB54T4FSDCNB0UVRXTJNMA44FMEQHB1OJ4YGWCBYZ
    //   &v=20180323
    //   &ll=23.793744,90.404816
    //   &radius=1000
    //   &section=food
    //   &limit=50
    @GET("venues/explore")
    Observable<HttpResponse> getRestureant(@Query("client_id") String clientId,
                                           @Query("client_secret") String clientSecret,
                                           @Query("v") String version,
                                           @Query("ll") String latLon,
                                           @Query("radius") String radius,
                                           @Query("section") String section,
                                           @Query("limit") String limit);
}
