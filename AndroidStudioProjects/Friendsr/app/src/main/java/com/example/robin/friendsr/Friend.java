package com.example.robin.friendsr;

import java.io.Serializable;

/**
 * Created by Robin on 19-2-2018.
 */

public class Friend implements Serializable {

    // initialize properties
    private String name, bio;
    private int drawableId;
    private float rating;

    // initialize constructor
    public Friend(String name, String bio, int drawableId) {
        this.name = name;
        this.bio = bio;
        this.drawableId = drawableId;
    }

    // declare getters of class
    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public float getRating() {
        return rating;
    }

    // declare setter
    public void setRating(float rating) {
        this.rating = rating;
    }
}
