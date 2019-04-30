package com.rimon.restaurantsnearby.base;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.rimon.restaurantsnearby.R;
import com.rimon.restaurantsnearby.base.navigation.ActivityScreenSwitcher;
import com.squareup.leakcanary.RefWatcher;

import javax.annotation.Nullable;
import javax.inject.Inject;

import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity extends AppCompatActivity {
    @Nullable
    private Toolbar mToolbar;

    @Inject
    ActivityScreenSwitcher mActivityScreenSwitcher;

    protected final String TAG = this.getClass().getSimpleName();
    private ViewDataBinding mDataBinding;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.setContentView(this, getLayoutResId());
        Timber.tag(TAG);
        injectBaseActivity();
        initToolbar();
        onCreateComponent();
        final Bundle mParams = getIntent().getExtras();
        if (mParams != null) {
            onExtractParams(mParams);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        mActivityScreenSwitcher.attach(this);
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        mActivityScreenSwitcher.detach();
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        destroyComponent();
        final RefWatcher mRefWatcher = BaseApplication.getRefWatcher(this);
        mRefWatcher.watch(this);
        super.onDestroy();
    }

    private void injectBaseActivity() {
        final BaseApplication mApplication = BaseApplication.getContext(this);
        mApplication.getBaseComponent().inject(this);
    }

    private void initToolbar() {
        int toolbarResId = resToolbarId();
        if (haveToolbar() && toolbarResId != 0) {
            mToolbar = findViewById(resToolbarId());
            setSupportActionBar(mToolbar);
            mToolbar.setNavigationOnClickListener(v -> onArrowClick());
            initToolbarForChildActivity(getSupportActionBar(), mToolbar);
        }
    }

    protected void onExtractParams(final Bundle mParams) {
    }

    protected void onArrowClick() {
        mActivityScreenSwitcher.goBack();
    }

    protected ActivityScreenSwitcher activityScreenSwitcher() {
        return mActivityScreenSwitcher;
    }

    protected Toolbar getToolbar() {
        if (mToolbar == null) {
            new Throwable(new NullPointerException(getResources().
                    getString(R.string.toolbar_null_pointer_exception)));
        }
        return mToolbar;
    }

    protected ViewDataBinding getViewDataBinding() {
        return mDataBinding;
    }

    public abstract int getLayoutResId();

    protected abstract void onCreateComponent();

    protected abstract boolean haveToolbar();

    protected abstract int resToolbarId();

    protected abstract void initToolbarForChildActivity(ActionBar mActionBar, Toolbar mToolbar);

    protected abstract void destroyComponent();
}
