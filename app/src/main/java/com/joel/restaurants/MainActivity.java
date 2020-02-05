package com.joel.restaurants;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mFindRestaurantsButton; //holds findRestaurantsButton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//runs androids default behaviours
        setContentView(R.layout.activity_main);//setContentView->> sets the activity to use in this case activity main

        mFindRestaurantsButton = (Button)findViewById(R.id.findRestaurantsButton);// findViewById takes id of button in layout and returns view
        mFindRestaurantsButton.setOnClickListener(new View.OnClickListener(){ //takes a new onClickListener as a parameter
            @Override
            public void onClick(View v){
                Toast.makeText(MainActivity.this,"Hello World!",Toast.LENGTH_LONG).show();

            }
        });
    }
}
