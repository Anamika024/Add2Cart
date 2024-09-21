package com.godspeed.gameschhalaang.product.model;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private static Cart instance;
    private Map<Product, Integer> cartItems = new HashMap<>();

    public Cart() {}

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void addProduct(Product product) {
        cartItems.put(product, cartItems.getOrDefault(product, 0) + 1);
    }

    public void removeProduct(Product product) {
        cartItems.remove(product);
    }

    public List<Product> getCartItems() {
        return new ArrayList<>(cartItems.keySet());
    }

    public int getQuantity(Product product) {
        return cartItems.getOrDefault(product, 0);
    }
}
