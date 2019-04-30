
package com.rimon.restaurantsnearby.model;

import com.squareup.moshi.Json;

import java.util.List;

public class Response {
    private List<Group> groups = null;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
