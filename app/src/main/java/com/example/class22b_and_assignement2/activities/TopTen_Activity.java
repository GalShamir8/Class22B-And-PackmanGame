package com.example.class22b_and_assignement2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.class22b_and_assignement2.R;
import com.example.class22b_and_assignement2.fragments.UsersFragment;

public class TopTen_Activity extends AppCompatActivity {
    private Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topten);
        data = getIntent().getExtras();
        loadFragmentUsers();
    }

    private void loadFragmentUsers() {
        Fragment usersFragment = new UsersFragment();
        usersFragment.setArguments(data);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.topTen_scroll, usersFragment).commit();
    }
}