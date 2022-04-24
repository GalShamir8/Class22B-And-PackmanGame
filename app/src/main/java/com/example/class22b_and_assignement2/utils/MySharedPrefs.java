package com.example.class22b_and_assignement2.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.class22b_and_assignement2.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class MySharedPrefs {
    private static final String FILE_NAME = "GameDateFile";
    private static final String USERS_KEY = "usersDate";

    private SharedPreferences sharedPrefs;
    private HashMap<String, User> users = new HashMap<>();
    private static MySharedPrefs myInstance = null;

    private MySharedPrefs(Context ctx) {
        sharedPrefs = ctx.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        getUsersData();
    }

    public static void init(Context ctx){
        if (myInstance == null){
            myInstance = new MySharedPrefs(ctx);
        }
    }

    public static MySharedPrefs getInstance(){
        return myInstance;
    }

    /**
     *
     * @return ArrayList of user data if users data exists else null
     */
    public ArrayList<User> getUsersData(){
        String json = sharedPrefs.getString(USERS_KEY, null);
        ArrayList<User> currentUsers = null;
        if (json != null) {
            currentUsers = new Gson().fromJson(
                    json,
                    new TypeToken<ArrayList<User>>()
                    {}.getType());
            for (User user: currentUsers){
                users.put(user.getName(), user);
            }
        }
        return currentUsers;
    }

    public void updateUserData(User user){
        users.put(user.getName(), user);
        ArrayList<User> newUsersData = new ArrayList<>(users.values());
        String json = new Gson().toJson(
                newUsersData,
                new TypeToken<ArrayList<User>>()
                {}.getType());
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(USERS_KEY, json);
        editor.apply();
    }

    /**
     *
     * @param username
     * @return User if exist or null
     */
    public User getUserByName(String username){
        User user = null;
        if (users != null){
            user = users.get(username);
        }
        return user;
    }
}
