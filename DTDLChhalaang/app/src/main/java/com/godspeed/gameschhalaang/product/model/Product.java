package com.godspeed.gameschhalaang.product.model;

public class Product {
    private String name;
    private String description;
    private double price;
    private int imageUrl;

    public Product(String name, String description, double price, int imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getImageUrl() {
        return imageUrl;
    }

}

