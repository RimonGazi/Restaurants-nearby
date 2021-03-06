package com.rimon.restaurantsnearby.base.navigation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class FragmentScreen implements Screen {
    public static final String BF_NAME = "FragmentScreen.name";

    @Nullable
    private Fragment mFragment;

    @Nullable
    public Fragment getFragment() {
        if (mFragment != null) {
            return mFragment;
        }
        mFragment = createFragment();
        if (mFragment == null) {
            throw new IllegalStateException("createFragment() returns null");
        }
        final Bundle mArguments = new Bundle();
        onAddArguments(mArguments);
        mFragment.setArguments(mArguments);
        return mFragment;
    }

    protected void onAddArguments(final Bundle mArguments) {
        mArguments.putString(BF_NAME, getName());
    }

    protected abstract Fragment createFragment();

    protected abstract String getName();
}
