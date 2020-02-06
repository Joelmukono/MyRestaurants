package com.joel.restaurants.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.joel.restaurants.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();

//    private Button mFindRestaurantsButton; //holds findRestaurantsButton
//    private EditText mLocationEditText; // holds locationEditText

    @BindView(R.id.findRestaurantsButton)
    Button mFindRestaurantsButton;
    @BindView(R.id.locationEditText)
    EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//runs androids default behaviours
        setContentView(R.layout.activity_main);//setContentView->> sets the activity to use in this case activity main
        ButterKnife.bind(this);

        mFindRestaurantsButton.setOnClickListener(this);//pass current context as argument

    }

    @Override
    public void onClick(View v) {
        if (v == mFindRestaurantsButton) {  //checks if view is onclick listener and applies click on condition
            String location = mLocationEditText.getText().toString(); // getText() grabs inputted value from edit text but returns executable data type that must be converted to string
            Toast.makeText(MainActivity.this, location, Toast.LENGTH_LONG).show();
            Log.d(TAG, location);
            Intent intent = new Intent(MainActivity.this, RestaurantsActivity.class); //context points to info and services needed by other objects
            intent.putExtra("location", location); //attaches data to intent to be passed to other activity
            startActivity(intent);

            //new Intent takes two params current context and activity(java class) we want to start
        }

    }
}
