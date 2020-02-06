package com.joel.restaurants.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.joel.restaurants.R;
import com.joel.restaurants.adapters.MyRestaurantsArrayAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantsActivity extends AppCompatActivity {
    private String[] restaurants = new String[] {"Mi Mero Mole", "Mother's Bistro",
            "Life of Pie", "Screen Door", "Luc Lac", "Sweet Basil",
            "Slappy Cakes", "Equinox", "Miss Delta's", "Andina",
            "Lardo", "Portland City Grill", "Fat Head's Brewery",
            "Chipotle", "Subway"};//array of items to dipslay in listview

    private String[] cuisines = new String[] {"Vegan Food", "Breakfast", "Fishs Dishs",
            "Scandinavian", "Coffee", "English Food", "Burgers", "Fast Food",
            "Noodle Soups", "Mexican", "BBQ", "Cuban", "Bar Food", "Sports Bar",
            "Breakfast", "Mexican" };

//    private TextView mLocationTextView;
//    private ListView mListView;

    @BindView(R.id.locationTextView) TextView mLocationTextView;
    @BindView(R.id.listView) ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        ButterKnife.bind(this);

        MyRestaurantsArrayAdapter adapter = new MyRestaurantsArrayAdapter(this, android.R.layout.simple_list_item_1, restaurants, cuisines);//creating a new array adapter takes arguments curret context, layout for rep in list item display,and array of items to show
        mListView.setAdapter(adapter);//takes individual items from arraylist and displaying them in mListView

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


    }
}
