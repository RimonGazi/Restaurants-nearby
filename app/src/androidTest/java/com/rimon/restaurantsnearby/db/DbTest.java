package com.rimon.restaurantsnearby.db;

import androidx.arch.core.executor.testing.CountingTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

abstract public class DbTest {
    @Rule
    public CountingTaskExecutorRule mCountingTaskExecutorRule = new CountingTaskExecutorRule();
    protected RestaurantDb db;

    @Before
    public void initDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                RestaurantDb.class).build();
    }

    @After
    public void closeDb() {
        db.close();
    }
}
