package com.example.class22b_and_assignement2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.class22b_and_assignement2.R;
import com.example.class22b_and_assignement2.fragments.UsersFragment;

public class TopTen_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topten);
        loadFragmentUsers();
        loadFragmentMap();
    }

    private void loadFragmentMap() {
        // TODO: 22/04/2022 add this when map implemented
    }

    private void loadFragmentUsers() {
        Fragment usersFragment = new UsersFragment();
        getSupportFragmentManager().beginTransaction().
                replace(R.id.topTen_scroll, usersFragment).commit();
    }
}