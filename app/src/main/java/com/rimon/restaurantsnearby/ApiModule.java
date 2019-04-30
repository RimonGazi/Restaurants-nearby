package com.rimon.restaurantsnearby;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.rimon.restaurantsnearby.network.ApiService;
import com.squareup.moshi.Moshi;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class ApiModule {
    private final HttpUrl PRODUCTION_API_URL = HttpUrl.parse("url/");

    @Provides
    @RestaurantScope
    HttpUrl provideBaseUrl() {
        return PRODUCTION_API_URL;
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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @RestaurantScope
    ApiService provideApiService(Retrofit mRetrofit) {
        return mRetrofit.create(ApiService.class);
    }
}
