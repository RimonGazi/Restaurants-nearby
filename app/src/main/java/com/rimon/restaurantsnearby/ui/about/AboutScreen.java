package com.rimon.restaurantsnearby.ui.about;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.rimon.restaurantsnearby.base.BaseActivity;
import com.rimon.restaurantsnearby.base.navigation.ActivityScreen;

public class AboutScreen extends ActivityScreen {
    public static final String NEED_TO_CLEAR_STACK = "AboutScreen.ClearStack";
    private final boolean needClearStack;

    public AboutScreen(boolean clearActivityStack) {
        this.needClearStack = clearActivityStack;
    }

    @Override
    protected Class<? extends BaseActivity> activityClass() {
        return AboutActivity.class;
    }

    @Override
    protected void configureIntent(@NonNull Intent intent) {
        if (needClearStack) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra(NEED_TO_CLEAR_STACK, needClearStack);
        }
    }
}
