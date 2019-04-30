package com.rimon.restaurantsnearby.base.dagger;


import com.rimon.restaurantsnearby.base.navigation.ActivityScreenSwitcher;
import com.rimon.restaurantsnearby.base.navigation.FragmentScreenSwitcher;

public interface BaseGraph {
    ActivityScreenSwitcher activityScreenSwitcher();

    FragmentScreenSwitcher fragmentScreenSwitcher();
}
