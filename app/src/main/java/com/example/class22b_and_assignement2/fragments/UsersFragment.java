package com.example.class22b_and_assignement2.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.class22b_and_assignement2.R;
import com.example.class22b_and_assignement2.utils.MySharedPrefs;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.Collections;

import com.example.class22b_and_assignement2.models.User;

public class UsersFragment extends Fragment {
    private static final int NUM_OF_ROWS = 10;
    private TableLayout users_TBL_usersTable;
    private ArrayList<TableRow> tableRows;
    private View view;
    private ArrayList<User> topTenUsers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_users, container, false);
        topTenUsers = new ArrayList<>();

        setTopTen();
        setViews();
        addTableRows();
        return view;
    }

//    private void setTopTen() {
//        Bundle data = getArguments();
//        if (data != null) {
//            ArrayList<String> usersJson = data.getStringArrayList("usersData");
//            ArrayList<User> usersData = new ArrayList<>();
//            for (String userJson: usersJson){
//                usersData.add(User.fromJsonToUser(userJson));
//            }
//            Collections.sort(usersData);
//            int iteration = Math.min(NUM_OF_ROWS, usersData.size());
//            for (int i = 0; i < iteration; i++) {
//                topTenUsers.add(usersData.get(i));
//            }
//        }
//    }
    private void setTopTen() {
        ArrayList <User> users = MySharedPrefs.getInstance().getUsersData();
        if (users != null){
            Collections.sort(users);
            int iterationLength = Math.min(NUM_OF_ROWS, users.size());
            for (int i = 0; i < iterationLength; i++) {
                topTenUsers.add(users.get(i));
            }
        }
    }

    private void addTableRows() {
        if (tableRows == null){
            tableRows = new ArrayList<>();
        }
        setHeaders();
        if(topTenUsers.size() == 0){
            TableRow tableRow = new TableRow(view.getContext());
            MaterialTextView noUsersTXT = new MaterialTextView(view.getContext());
            noUsersTXT.setText("No users records yet...");
            noUsersTXT.setPadding(16, 16, 16, 16);
            tableRow.addView(noUsersTXT);

            users_TBL_usersTable.addView(tableRow);
        }else {
            for (User user : this.topTenUsers) {
                TableRow tableRow = new TableRow(view.getContext());
                MaterialTextView nameColData = new MaterialTextView(view.getContext());
                MaterialTextView scoreColData = new MaterialTextView(view.getContext());
                MaterialTextView locationColData = new MaterialTextView(view.getContext());

                nameColData.setText(user.getName());
                scoreColData.setText(String.valueOf(user.getScore()));
                // TODO: 23/04/2022 add location implementation
                // locationColData.setText(user.getLocation().getAddressName());

                nameColData.setPadding(16,16,16,16 );
                scoreColData.setPadding(16,16,16,16);
                // locationColData.setPadding(16,16,16,16);


                tableRow.addView(nameColData);
                tableRow.addView(scoreColData);
                tableRow.addView(locationColData);

                users_TBL_usersTable.addView(tableRow);
            }
        }

    }

    private void setHeaders() {
        TableRow tableRow = new TableRow(view.getContext());

        MaterialTextView nameCol = new MaterialTextView(view.getContext());
        MaterialTextView scoreCol = new MaterialTextView(view.getContext());
        MaterialTextView locationCol = new MaterialTextView(view.getContext());

        nameCol.setText("Name");
        nameCol.setTextSize(20);
        nameCol.setPadding(16,16,16,16);

        scoreCol.setText("Score");
        scoreCol.setTextSize(20);
        scoreCol.setPadding(16,16,16,16);

        locationCol.setText("Location");
        locationCol.setTextSize(20);
        locationCol.setPadding(16,16,16,16);

        tableRow.addView(nameCol);
        tableRow.addView(scoreCol);
        tableRow.addView(locationCol);

        users_TBL_usersTable.addView(tableRow);
    }

    private void setViews() {
        users_TBL_usersTable = view.findViewById(R.id.users_TBL_usersTable);
    }
}