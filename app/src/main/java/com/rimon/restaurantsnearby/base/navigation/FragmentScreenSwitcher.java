package com.rimon.restaurantsnearby.base.navigation;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.rimon.restaurantsnearby.base.BaseFragment;
import com.rimon.restaurantsnearby.utils.Strings;

import java.util.List;

public class FragmentScreenSwitcher implements ScreenSwitcher<FragmentScreen>,
        FragmentManager.OnBackStackChangedListener {
    private HasFragmentContainer mActivity;

    @IdRes
    private int mFragmentContainerId;

    private FragmentManager mFragmentManager;

    @Nullable
    private String mResultFragmentTag;

    @Nullable
    private Integer mRequestCode;

    public void attach(HasFragmentContainer mActivity) {
        this.mActivity = mActivity;
        this.mFragmentContainerId = mActivity.getFragmentContainerId();
        this.mFragmentManager = mActivity.getManager();
        this.mFragmentManager.addOnBackStackChangedListener(this);
    }

    public boolean hasFragments() {
        final List<Fragment> mFragments = mFragmentManager.getFragments();
        return mFragments != null && !mFragments.isEmpty();
    }

    public List<Fragment> getFragments() {
        return mFragmentManager.getFragments();
    }

    @Override
    public void open(final FragmentScreen mScreen) {
        mResultFragmentTag = mScreen.getName();
        final List<Fragment> fragments = mFragmentManager.getFragments();
        if (fragments == null || fragments.isEmpty()) {
            replace(mScreen);
        } else {
            add(mScreen);
        }
    }

    public boolean openWithClearStackCheck(final FragmentScreen mScreen) {
        if (!mScreen.getName().equals(mResultFragmentTag)) {
            openWithClearStack(mScreen);
            return true;
        }
        return false;
    }

    public void openWithClearStack(FragmentScreen mFragmentScreen) {
        final int mBackStackEntryCount = mFragmentManager.getBackStackEntryCount();
        for (int mIndex = 0; mIndex < mBackStackEntryCount; mIndex++) {
            mFragmentManager.popBackStack();
        }
        mResultFragmentTag = mFragmentScreen.getName();
        replace(mFragmentScreen);
        final Fragment mNewFragment = mFragmentScreen.getFragment();
        if (mNewFragment instanceof BaseFragment) {
            BaseFragment mBaseFragment = (BaseFragment) mNewFragment;
            if (mNewFragment != null) {
                mBaseFragment.setAfterClearStack(true);
                //after replace don`t called "onBackStackChanged()", consequently, don`t called "onShow();
            }
        }
    }

    public void startForResult(final FragmentScreen mScreen, int mRequestCode) {
        final int mBackStackEntryCount = mFragmentManager.getBackStackEntryCount();
        if (mBackStackEntryCount == 0) {
            mResultFragmentTag = mFragmentManager.findFragmentById(mFragmentContainerId).getTag();
        } else {
            mResultFragmentTag = mFragmentManager
                    .getBackStackEntryAt(mBackStackEntryCount - 1).getName();
        }
        this.mRequestCode = mRequestCode;
        add(mScreen);
    }

    private void replace(final FragmentScreen mScreen) {
        mFragmentManager.beginTransaction()
                .replace(mFragmentContainerId, mScreen.getFragment(), mScreen.getName())
                .commit();
    }

    private void add(final FragmentScreen mScreen) {
        final String mName = mScreen.getName();
        mFragmentManager.beginTransaction()
                .add(mFragmentContainerId, mScreen.getFragment(), mName)
                .addToBackStack(mName)
                .commit();
    }

    @Override
    public void goBack() {
        mFragmentManager.popBackStack();
    }

    public void detach() {
        mActivity = null;
        if (mFragmentManager != null) {
            mFragmentManager.removeOnBackStackChangedListener(this);
        }
        mFragmentManager = null;
        mFragmentContainerId = 0;
        mResultFragmentTag = "";
        mRequestCode = null;
    }

    public void onFragmentResult(final Intent mIntent) {
        if (!Strings.isBlank(mResultFragmentTag) && mRequestCode != null) {
            final Fragment mFragmentByTag = mFragmentManager.findFragmentByTag(mResultFragmentTag);
            if (mFragmentByTag != null) {
                mFragmentByTag.onActivityResult(mRequestCode, Activity.RESULT_OK, mIntent);
            }
            mRequestCode = null;
        }
        goBack();
    }

    @Override
    public void onBackStackChanged() {
        final String mCurrentFragmentTag;
        final int mBackStackEntryCount = mFragmentManager.getBackStackEntryCount();
        if (mBackStackEntryCount == 0) {
            mCurrentFragmentTag = mFragmentManager.findFragmentById(mFragmentContainerId).getTag();
        } else {
            mCurrentFragmentTag = mFragmentManager
                    .getBackStackEntryAt(mBackStackEntryCount - 1).getName();
        }

        final Fragment mNewFragment = mFragmentManager
                .findFragmentByTag(mCurrentFragmentTag);
        if (mNewFragment instanceof BaseFragment) {
            BaseFragment mBaseFragment = (BaseFragment) mNewFragment;
            final BaseFragment mCurrentFragment = (BaseFragment) mBaseFragment;
            if (mCurrentFragment != null) {
                mCurrentFragment.onShow();
            }
        }
    }

    public int getBackStackCount() {
        return mFragmentManager.getBackStackEntryCount();
    }
}
