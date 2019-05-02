package com.rimon.restaurantsnearby.db;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.runner.AndroidJUnit4;

import com.rimon.restaurantsnearby.LiveDataTestUtil;
import com.rimon.restaurantsnearby.TestUtil;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
public class RestaurantDaoTest extends DbTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void insertAndLoad() throws InterruptedException, TimeoutException {
        final String id = "id";
        final String name = "name";
        final double lat = 12;
        final double lng = 23;
        final String iconUrl = "url";

        final Restaurant mRestaurant = TestUtil.createRestaurant(id, name, lat, lng, iconUrl);
        final List<Restaurant> mList = new ArrayList<>();
        mList.add(mRestaurant);

        db.restaurantDao().insertAll(mList);

        final List<Restaurant> loaded = LiveDataTestUtil.getValue(db.restaurantDao().loadAll());
        assertThat(loaded.size(), is(mList.size()));

        final Restaurant mRetrieveRestaurant = loaded.get(0);

        assertThat(mRetrieveRestaurant.id, is(id));
        assertThat(mRetrieveRestaurant.name, is(name));
        assertThat(mRetrieveRestaurant.lat, is(lat));
        assertThat(mRetrieveRestaurant.lng, is(lng));
        assertThat(mRetrieveRestaurant.iconUrl, is(iconUrl));
    }
}
