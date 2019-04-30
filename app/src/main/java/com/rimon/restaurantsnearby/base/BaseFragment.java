package com.rimon.restaurantsnearby.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.squareup.leakcanary.RefWatcher;

import timber.log.Timber;

public abstract class BaseFragment extends Fragment {
    protected final String TAG = this.getClass().getSimpleName();
    private boolean mAfterClearStack = false;
    private ViewDataBinding mDataBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.tag(TAG);
        injectToActivityComponent();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int layoutId = layoutId();
        mDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false);
        final View mView = mDataBinding.getRoot();
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        final Bundle mBundle = getArguments();
        onExtractParams(mBundle);
        if (mAfterClearStack) {
            onShow();
        }
    }

    protected void onExtractParams(final Bundle mBundle) {
    }

    public void onFragmentResult(int requestCode, Bundle params) {
    }

    public void setAfterClearStack(final boolean mAfterClearStack) {
        this.mAfterClearStack = mAfterClearStack;
    }

    @Override
    public void onDestroyView() {
        final RefWatcher mRefWatcher = BaseApplication.getRefWatcher(getActivity());
        mRefWatcher.watch(this);
        super.onDestroyView();
    }

    protected ViewDataBinding getViewDataBinding() {
        return mDataBinding;
    }

    @LayoutRes
    protected abstract int layoutId();

    protected abstract void injectToActivityComponent();

    public abstract void onShow();
}
