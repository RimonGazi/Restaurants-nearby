package com.rimon.restaurantsnearby.base;

import android.content.Context;
import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.rimon.restaurantsnearby.BuildConfig;
import com.rimon.restaurantsnearby.R;
import com.rimon.restaurantsnearby.base.dagger.BaseComponent;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class BaseApplication extends MultiDexApplication {
    private final String DEFAULT_FONT_PATH = "font/android_7.ttf";
    private BaseComponent mBaseComponent;
    private RefWatcher baseRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        initCalligraphy();
        initTimber();
        initDependencyInjection();
        baseRefWatcher = installLeakCanary();
    }

    private void initCalligraphy() {
        final CalligraphyConfig mCalligraphyConfig = new CalligraphyConfig.Builder()
                // .setDefaultFontPath(DEFAULT_FONT_PATH) // Todo: 04/30/2019 (GAZI RIMON) add default here
                .setFontAttrId(R.attr.fontPath)
                .build();
        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            final Timber.DebugTree mdDebugTree = new Timber.DebugTree();
            Timber.plant(mdDebugTree);
        } else {
            final CrashReportingTree mCrashReportingTree = new CrashReportingTree();
            Timber.plant(mCrashReportingTree);
        }
    }

    private void initDependencyInjection() {
        mBaseComponent = BaseComponent.Initializer.init(this);
        mBaseComponent.inject(this);
    }

    public BaseComponent getBaseComponent() {
        return mBaseComponent;
    }

    public static BaseApplication getContext(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }

    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(final int mPriority, final String mTag,
                           final String message, final Throwable mThrowable) {
            if (mPriority == Log.VERBOSE || mPriority == Log.DEBUG) {
                return;
            }
            CrashLibrary.log(mPriority, mTag, message);
            if (mThrowable != null) {
                if (mPriority == Log.ERROR) {
                    CrashLibrary.logError(mThrowable);
                } else if (mPriority == Log.WARN) {
                    CrashLibrary.logWarning(mThrowable);
                }
            }
        }
    }

    private RefWatcher installLeakCanary() {
        if (BuildConfig.DEBUG) {
            return LeakCanary.install(this);
        } else {
            return RefWatcher.DISABLED;
        }
    }

    public static RefWatcher getRefWatcher(final Context mContext) {
        final BaseApplication mApplication = (BaseApplication) mContext.getApplicationContext();
        return mApplication.baseRefWatcher;
    }
}
