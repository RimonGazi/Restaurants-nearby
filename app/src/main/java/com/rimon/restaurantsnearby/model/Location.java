
package com.rimon.restaurantsnearby.model;

import java.util.List;
import com.squareup.moshi.Json;

public class Location {
    @Json(name = "lat")
    private Double lat;
    @Json(name = "lng")
    private Double lng;
    @Json(name = "labeledLatLngs")
    private List<LabeledLatLng> labeledLatLngs = null;
    @Json(name = "distance")
    private Integer distance;
    @Json(name = "cc")
    private String cc;
    @Json(name = "country")
    private String country;
    @Json(name = "formattedAddress")
    private List<String> formattedAddress = null;
    @Json(name = "address")
    private String address;
    @Json(name = "city")
    private String city;
    @Json(name = "state")
    private String state;
    @Json(name = "crossStreet")
    private String crossStreet;
    @Json(name = "neighborhood")
    private String neighborhood;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public List<LabeledLatLng> getLabeledLatLngs() {
        return labeledLatLngs;
    }

    public void setLabeledLatLngs(List<LabeledLatLng> labeledLatLngs) {
        this.labeledLatLngs = labeledLatLngs;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(List<String> formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCrossStreet() {
        return crossStreet;
    }

    public void setCrossStreet(String crossStreet) {
        this.crossStreet = crossStreet;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

}
