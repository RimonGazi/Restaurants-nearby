package com.rimon.restaurantsnearby.ui.about;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.rimon.restaurantsnearby.R;
import com.rimon.restaurantsnearby.base.BaseActivity;
import com.rimon.restaurantsnearby.databinding.ActivityAboutBinding;

public class AboutActivity extends BaseActivity {

    @Override
    public int getLayoutResId() {
        return R.layout.activity_about;
    }

    @Override
    protected void onCreateComponent() {

    }

    @Override
    protected boolean haveToolbar() {
        return true;
    }

    @Override
    protected int resToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected void initToolbarForChildActivity(ActionBar mActionBar, Toolbar mToolbar) {
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityAboutBinding mBinding = (ActivityAboutBinding) getViewDataBinding();
        mBinding.webView.loadUrl("file:///android_asset/about.html");
    }

    @Override
    protected void destroyComponent() {

    }
}
