
package com.rimon.restaurantsnearby.model;

import com.squareup.moshi.Json;

import java.util.List;

public class Venue {
    @Json(name = "id")
    private String id;
    @Json(name = "name")
    private String name;
    @Json(name = "location")
    private Location location;
    @Json(name = "categories")
    private List<Category> categories = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
