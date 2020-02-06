package com.joel.restaurants.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.joel.restaurants.Business;
import com.joel.restaurants.Category;
import com.joel.restaurants.R;
import com.joel.restaurants.YelpApi;
import com.joel.restaurants.YelpBusinessesSearchResponse;
import com.joel.restaurants.YelpClient;
import com.joel.restaurants.adapters.MyRestaurantsArrayAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantsActivity extends AppCompatActivity {


//    private TextView mLocationTextView;
//    private ListView mListView;

    @BindView(R.id.locationTextView) TextView mLocationTextView;
    @BindView(R.id.listView) ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        ButterKnife.bind(this);

//        MyRestaurantsArrayAdapter adapter = new MyRestaurantsArrayAdapter(this, android.R.layout.simple_list_item_1, restaurants, cuisines);//creating a new array adapter takes arguments curret context, layout for rep in list item display,and array of items to show
//        mListView.setAdapter(adapter);//takes individual items from arraylist and displaying them in mListView

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String  restaurant = ((TextView)view).getText().toString();
                Toast.makeText(RestaurantsActivity.this,restaurant,Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent(); //recreates intent from main activity
        String location = intent.getStringExtra("location"); // pulls out "location" key value pair from put extra in mainactivity
        mLocationTextView.setText("Here are all the restaurants near: " + location);//setText updates mLocationTextView with content from location,location is added as argument

        YelpApi client = YelpClient.getClient();

        Call<YelpBusinessesSearchResponse> call = client.getRestaurants(location,"restaurants");//location is the value from the user,getRestaurants is method in YelpApi

        call.enqueue(new Callback<YelpBusinessesSearchResponse>(){//The enqueue method takes an object with implements the Callback interface
            @Override
            public void onResponse(Call<YelpBusinessesSearchResponse> call, Response<YelpBusinessesSearchResponse> response){ //onResponse invoked when an HTTP response is received
                if(response.isSuccessful()){  // isSuccessful confirms if response was successful
                    List<Business> restaurantsList = response.body().getBusinesses();
                    String[] restaurants = new String[restaurantsList.size()];
                    String[] categories = new String[restaurantsList.size()];

                    for(int i = 0; i<restaurants.length;i++){
                        restaurants[i] = restaurantsList.get(i).getName();
                    }

                    for(int i = 0;i<categories.length;i++){
                        Category category = restaurantsList.get(i).getCategories().get(0);
                        categories[i] = category.getTitle();
                    }

                    ArrayAdapter adapter
                            = new MyRestaurantsArrayAdapter(RestaurantsActivity.this, android.R.layout.simple_list_item_1, restaurants, categories);
                    mListView.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {  //onFailure which is invoked when a network exception occurred talking to the server or when an unexpected exception occurred creating the request or processing the response.

            }
        });
    }

}
