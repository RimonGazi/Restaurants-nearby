package com.rimon.restaurantsnearby;

import android.app.Application;
import android.content.Context;

import androidx.test.runner.AndroidJUnit4;
import androidx.test.runner.AndroidJUnitRunner;

import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RestaurantInstrumentedTest extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, TestApp.class.getName(), context);
    }
}
