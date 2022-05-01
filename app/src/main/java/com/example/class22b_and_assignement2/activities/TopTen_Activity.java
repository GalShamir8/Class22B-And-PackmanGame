package com.example.class22b_and_assignement2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.class22b_and_assignement2.fragments.MapsFragment;
import com.example.class22b_and_assignement2.R;
import com.example.class22b_and_assignement2.fragments.UsersFragment;

public class TopTen_Activity extends AppCompatActivity {
    private MapsFragment mapsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topten);
        loadFragmentUsers();
        loadFragmentMap();
    }

    private void loadFragmentMap() {
        mapsFragment = new MapsFragment();
        getSupportFragmentManager().beginTransaction().
                replace(R.id.topTen_map, mapsFragment).commit();
    }

    private void loadFragmentUsers() {
        UsersFragment usersFragment = new UsersFragment();
        usersFragment.registerMapCallback((lon, lat, addressName) ->
                mapsFragment.addMarkerToMap(lon, lat, addressName));

        getSupportFragmentManager().beginTransaction().
                replace(R.id.topTen_scroll, usersFragment).commit();
    }
}