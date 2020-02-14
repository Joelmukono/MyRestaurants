package com.joel.restaurants.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.joel.restaurants.Constants;
import com.joel.restaurants.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();

//    private Button mFindRestaurantsButton; //holds findRestaurantsButton
//    private EditText mLocationEditText; // holds locationEditText

//    private SharedPreferences mSharedPreferences;
//    private  SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedLocationReference;
    private ValueEventListener mSearchedLocationReferenceListener;

    @BindView(R.id.savedRestaurantsButton) Button mSavedRestaurantsButton;
    @BindView(R.id.findRestaurantsButton)
    Button mFindRestaurantsButton;
    @BindView(R.id.locationEditText)
    EditText mLocationEditText;
    @BindView(R.id.appNameTextView)
    TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedLocationReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);

        mSearchedLocationReferenceListener = mSearchedLocationReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot locationSnapshot : dataSnapshot.getChildren()){
                    String location = locationSnapshot.getValue().toString();
                    Log.d("Locations updated","location: "+ location);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        super.onCreate(savedInstanceState);//runs androids default behaviours
        setContentView(R.layout.activity_main);//setContentView->> sets the activity to use in this case activity main
        ButterKnife.bind(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this); //stored shared preference tool
//        mEditor = mSharedPreferences.edit();



        mFindRestaurantsButton.setOnClickListener(this);//pass current context as argument
        mSavedRestaurantsButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mFindRestaurantsButton) {  //checks if view is onclick listener and applies click on condition
            String location = mLocationEditText.getText().toString(); // getText() grabs inputted value from edit text but returns executable data type that must be converted to string

            saveLocationToFirebase(location);

//            if(!(location).equals("")){
//                addToSharedPreferences(location);
//            }
            Toast.makeText(MainActivity.this, location, Toast.LENGTH_LONG).show();
            Log.d(TAG, location);
            Intent intent = new Intent(MainActivity.this, RestaurantsActivity.class); //context points to info and services needed by other objects
            intent.putExtra("location", location); //attaches data to intent to be passed to other activity
            startActivity(intent);

            //new Intent takes two params current context and activity(java class) we want to start


        }
        if (v == mSavedRestaurantsButton) {
            Intent intent = new Intent(MainActivity.this, SavedRestaurantListActivity.class);
            startActivity(intent);
        }

    }
    private void saveLocationToFirebase(String location){
        mSearchedLocationReference.push().setValue(location); // push generates push id to avoid overwriting the database
    }

//    private  void addToSharedPreferences(String location){ //calls editor to write info to shared preferences.
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY,location).apply(); // apply saves information
//    }


    @Override
    protected  void onDestroy(){
        super.onDestroy();
        mSearchedLocationReference.removeEventListener(mSearchedLocationReferenceListener);
    }
}
