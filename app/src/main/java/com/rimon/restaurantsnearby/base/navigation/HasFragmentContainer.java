package com.rimon.restaurantsnearby.base.navigation;

import androidx.annotation.IdRes;
import androidx.fragment.app.FragmentManager;

public interface HasFragmentContainer {
    @IdRes
    int getFragmentContainerId();

    FragmentManager getManager();
}
