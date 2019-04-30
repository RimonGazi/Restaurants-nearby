
package com.rimon.restaurantsnearby.model;

import com.squareup.moshi.Json;

import java.util.List;

public class Group {
    @Json(name = "items")
    private List<Item> items = null;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
