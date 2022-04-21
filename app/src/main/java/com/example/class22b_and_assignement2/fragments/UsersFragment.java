package com.example.class22b_and_assignement2.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.class22b_and_assignement2.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class UsersFragment extends Fragment {
    private static final int NUM_OF_COLS = 3;
    private static final int NUM_OF_ROWS = 10;
    private TableLayout users_TBL_usersTable;
    private ArrayList<TableRow> tableRows;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_users, container, false);
        setViews();
        addTableRows();
        return view;
    }

    private void addTableRows() {
        if (tableRows == null){
            tableRows = new ArrayList<>();
        }
        setHeaders();

        for (int i = 0; i < NUM_OF_ROWS; i++) {
            TableRow tableRow = new TableRow(view.getContext());
            for (int j = 0; j < NUM_OF_COLS; j++) {
                // TODO: 21/04/2022 add get data from bundle
                MaterialTextView colData = new MaterialTextView(view.getContext());
                colData.setText("gal");
                colData.setTextSize(20);
                colData.setPadding(16,16,16,16);

                tableRow.addView(colData);
            }
            users_TBL_usersTable.addView(tableRow);
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