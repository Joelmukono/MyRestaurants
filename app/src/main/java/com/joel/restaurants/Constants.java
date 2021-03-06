package com.joel.restaurants;

import com.joel.restaurants.BuildConfig;

public class Constants {
    public static final String PREFERENCES_LOCATION_KEY = "location";
    public static final String FIREBASE_CHILD_RESTAURANTS = "restaurants";
    public static final String FIREBASE_CHILD_SEARCHED_LOCATION = "searchedLocation";
    public static final String YELP_BASE_URL = "https://api.yelp.com/v3/"; //set the base url
    public static final String YELP_API_KEY = BuildConfig.YELP_API_KEY;   //connects to BuildConfig and fetches apikey from gradle.properties.
}
