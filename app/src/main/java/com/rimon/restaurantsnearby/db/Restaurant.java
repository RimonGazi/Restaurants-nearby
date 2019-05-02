package com.rimon.restaurantsnearby.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = "id")
public class Restaurant {
    @NonNull
    public String id;
    public String name;
    public double lat;
    public double lng;
    public String iconUrl;
}
