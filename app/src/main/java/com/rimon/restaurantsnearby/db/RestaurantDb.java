package com.rimon.restaurantsnearby.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Restaurant.class}, version = 1)
public abstract class RestaurantDb extends RoomDatabase {
    abstract public RestaurantDao restaurantDao();

    public static final String NAME = "RestaurantDb";
}
