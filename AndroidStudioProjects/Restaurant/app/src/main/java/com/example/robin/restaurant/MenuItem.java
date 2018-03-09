package com.example.robin.restaurant;

import java.io.Serializable;

/**
 * Created by Robin on 8-3-2018.
 */

public class MenuItem implements Serializable {

    // define variables
    private String name;
    private String description;
    private String imageUrl;
    private long price;
    private String category;

    // define constructor of the class
    public MenuItem(String name, String description, String imageUrl, long price, String category) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.category = category;
    }

    // define setters and getters of the class
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
