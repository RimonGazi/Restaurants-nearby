
package com.rimon.restaurantsnearby.model;

import com.squareup.moshi.Json;

public class Item {
    @Json(name = "venue")
    private Venue venue;

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
