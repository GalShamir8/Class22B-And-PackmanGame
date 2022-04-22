package models;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.NotSerializableException;
import java.io.Serializable;

public class User {
    private String name;
    private UserLocation location;
    private int score;

    public User() {

    }

    public User(String name, int score) {
        setName(name);
        setScore(score);
        location = new UserLocation();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserLocation getLocation() {
        return location;
    }

    public void setLocation(UserLocation location) {
        this.location = location;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String userToJson(){
        try{
            Gson gson = new Gson();
            return gson.toJson(this);
        }catch (Exception e){
            Log.e("ERROR","Failed to parse from User to json\n" + e.getMessage());
        }
        return "";
    }

    public User fromJsonToUser(String json){
        User user = new User();
        try{
            Gson gson = new Gson();
            user = gson.fromJson(json, this.getClass());
        }catch (Exception e){
            Log.e("ERROR","Failed to parse from json to User\n" + e.getMessage());
        }
        return user;
    }
}
