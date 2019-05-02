package com.rimon.restaurantsnearby.ui.adapter;

public class SuggestionModel {
    private final String mId;
    private final String mTitle;

    public SuggestionModel(String mId, String mTitle) {
        this.mId = mId;
        this.mTitle = mTitle;
    }

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }
}
