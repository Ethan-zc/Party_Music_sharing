package com.csci5115.activities;

/**
 * Created by karanjaswani on 1/12/18.
 */

public class Product {
    private int id;
    private String title;
    private String shortdesc;
    private String rating;
    private String price;
    int votes;
    private int image;

    public Product(int id, String title, String shortdesc, String rating, String price, int votes, int image) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.price = price;
        this.votes = votes;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public String getRating() {
        return rating;
    }

    public String getPrice() { return price; }

    public int getVotes() { return votes; }

    public int getImage() {
        return image;
    }
}
