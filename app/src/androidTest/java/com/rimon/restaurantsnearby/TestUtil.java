package com.rimon.restaurantsnearby;

import com.rimon.restaurantsnearby.db.Restaurant;

public class TestUtil {

    public static Restaurant createRestaurant(String id, String name, double lat, double lng, String iconUlr) {
        Restaurant restaurant = new Restaurant();
        restaurant.id = id;
        restaurant.name = name;
        restaurant.lat = lat;
        restaurant.lng = lng;
        restaurant.iconUrl = iconUlr;
        return restaurant;
    }
}
