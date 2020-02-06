package com.joel.restaurants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static  final String TAG = MainActivity.class.getSimpleName();

    private Button mFindRestaurantsButton; //holds findRestaurantsButton
    private EditText mLocationEditText; // holds locationEditText

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//runs androids default behaviours
        setContentView(R.layout.activity_main);//setContentView->> sets the activity to use in this case activity main

        mLocationEditText = (EditText) findViewById(R.id.locationEditText);

        mFindRestaurantsButton = (Button)findViewById(R.id.findRestaurantsButton);// findViewById takes id of button in layout and returns view
        mFindRestaurantsButton.setOnClickListener(new View.OnClickListener(){ //takes a new onClickListener as a parameter
            @Override
            public void onClick(View v){
                String location = mLocationEditText.getText().toString(); // getText() grabs inputted value from edit text but returns executable data type that must be converted to string
                Toast.makeText(MainActivity.this, location,Toast.LENGTH_LONG).show();
                Log.d(TAG,location);
                Intent intent = new Intent(MainActivity.this,RestaurantsActivity.class); //context points to info and services needed by other objects
                //startActivity(intent);

                //new Intent takes two params current context and activity(java class) we want to start
            }
        });
    }
}
