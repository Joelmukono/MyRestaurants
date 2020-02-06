package com.joel.restaurants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.joel.restaurants.BuildConfig.YELP_API_KEY;
import static com.joel.restaurants.Constants.YELP_BASE_URL;

public class YelpClient {

    private static Retrofit retrofit = null;


    public static YelpApi getClient() {

        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor(){  //The OkHttpClient intercepts each request and adds an HTTP Authorization header with the value of the Yelp API token

                @Override
                public Response intercept(Chain chain) throws IOException{
                    Request newRequest = chain.request().newBuilder().addHeader("Authorization",YELP_API_KEY).build();//This ensures that each request we make is properly authenticated

                    return chain.proceed(newRequest);
                }

            }).build();

            retrofit = new Retrofit.Builder().baseUrl(YELP_BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();//The converter handles data serialization from JSON to Java objects
        }
        return retrofit.create(YelpApi.class);

    }
}
