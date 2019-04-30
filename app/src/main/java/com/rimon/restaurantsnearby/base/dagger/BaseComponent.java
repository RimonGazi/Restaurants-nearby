package com.rimon.restaurantsnearby.base.dagger;

import com.rimon.restaurantsnearby.base.BaseActivity;
import com.rimon.restaurantsnearby.base.BaseApplication;
import com.rimon.restaurantsnearby.base.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

@BaseScope
@Singleton
@Component(modules = BaseModule.class)
public interface BaseComponent extends BaseGraph {
    void inject(final BaseApplication mBaseApplication);

    void inject(final BaseActivity mBaseActivity);

    void inject(final BaseFragment mBaseFragment);

    final class Initializer {
        private Initializer() {
        }

        public static BaseComponent init(final BaseApplication mBaseApplication) {
            return DaggerBaseComponent.builder()
                    .baseModule(new BaseModule(mBaseApplication))
                    .build();
        }
    }
}
