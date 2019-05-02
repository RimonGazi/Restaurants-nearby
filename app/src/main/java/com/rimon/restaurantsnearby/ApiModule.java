package com.rimon.restaurantsnearby;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.rimon.restaurantsnearby.network.ApiService;
import com.rimon.restaurantsnearby.utils.LiveDataCallAdapterFactory;
import com.squareup.moshi.Moshi;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class ApiModule {
    private final HttpUrl API_URL = HttpUrl.parse("https://api.foursquare.com/v2/");

    @Provides
    @RestaurantScope
    HttpUrl provideBaseUrl() {
        return API_URL;
    }

    @Provides
    @RestaurantScope
    Moshi provideMoshi() {
        return new Moshi.Builder().build();
    }

    @Provides
    @RestaurantScope
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }

    @Provides
    @RestaurantScope
    Retrofit provideRetrofit(HttpUrl mBaseUrl, OkHttpClient mClient, Moshi mMoshi) {
        return new Retrofit.Builder()
                .client(mClient)
                .baseUrl(mBaseUrl)
                .addConverterFactory(MoshiConverterFactory.create(mMoshi))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build();
    }

    @Provides
    @RestaurantScope
    ApiService provideApiService(Retrofit mRetrofit) {
        return mRetrofit.create(ApiService.class);
    }
}
