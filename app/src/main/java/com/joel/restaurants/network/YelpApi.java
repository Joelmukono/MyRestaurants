package com.joel.restaurants.network;

import com.joel.restaurants.network.YelpBusinessesSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YelpApi {

    @GET("businesses/search") // endpoint bieng queried
    Call<YelpBusinessesSearchResponse> getRestaurants(
            @Query("location") String location, //"location isthe query parameter to the request"
            @Query("term") String term
    );
}
