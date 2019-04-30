package com.rimon.restaurantsnearby.base.navigation;

public interface ScreenSwitcher<S extends Screen> {
    void open(S screen);

    void goBack();
}
