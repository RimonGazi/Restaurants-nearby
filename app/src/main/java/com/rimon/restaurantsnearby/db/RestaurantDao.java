package com.rimon.restaurantsnearby.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RestaurantDao {
    @Query("SELECT * FROM restaurant")
    LiveData<List<Restaurant>> loadAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Restaurant> mRestaurants);
}
