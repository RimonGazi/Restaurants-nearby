package com.rimon.restaurantsnearby;

import android.content.Context;

import com.facebook.stetho.Stetho;
import com.rimon.restaurantsnearby.base.BaseApplication;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class RestaurantApplication extends BaseApplication {
    private RestaurantComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        buildComponentAndInject();
    }

    private RefWatcher installLeakCanary() {
        if (BuildConfig.DEBUG) {
            return LeakCanary.install(this);
        } else {
            return RefWatcher.DISABLED;
        }
    }

    private void buildComponentAndInject() {
        mComponent = RestaurantComponent.Initializer.init(this);
        mComponent.inject(this);
    }

    public RestaurantComponent getAppComponent() {
        return mComponent;
    }

    public static RestaurantApplication get(final Context mContext) {
        return (RestaurantApplication) mContext.getApplicationContext();
    }
}
