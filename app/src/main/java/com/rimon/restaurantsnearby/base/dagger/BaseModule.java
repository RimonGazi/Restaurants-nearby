package com.rimon.restaurantsnearby.base.dagger;

import com.rimon.restaurantsnearby.base.BaseApplication;
import com.rimon.restaurantsnearby.base.navigation.ActivityScreenSwitcher;
import com.rimon.restaurantsnearby.base.navigation.FragmentScreenSwitcher;

import dagger.Module;
import dagger.Provides;

@Module
public class BaseModule {
    private final BaseApplication application;

    public BaseModule(BaseApplication application) {
        this.application = application;
    }

    @Provides
    @BaseScope
    BaseApplication provideBaseContext() {
        return this.application;
    }

    @Provides
    @BaseScope
    ActivityScreenSwitcher provideActivityScreenSwitcher() {
        return new ActivityScreenSwitcher();
    }

    @Provides
    @BaseScope
    FragmentScreenSwitcher provideFragmentScreenSwitcher() {
        return new FragmentScreenSwitcher();
    }
}
