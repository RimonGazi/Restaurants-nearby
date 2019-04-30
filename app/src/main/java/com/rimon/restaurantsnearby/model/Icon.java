
package com.rimon.restaurantsnearby.model;

import com.squareup.moshi.Json;

public class Icon {
    @Json(name = "prefix")
    private String prefix;
    @Json(name = "suffix")
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

}
