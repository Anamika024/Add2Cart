package com.godspeed.gameschhalaang.home.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.godspeed.gameschhalaang.product.adapter.CartAdapter;
import com.godspeed.gameschhalaang.product.model.Cart;
import com.godspeed.gameschhalaang.product.model.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.weforwins.chhalaang.R;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CartFragment extends Fragment {

    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private TextView emptyCartTextView;
    private ImageView qrCodeImageView;
//    private FirebaseFirestore db;
    private Button share;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        emptyCartTextView = view.findViewById(R.id.emptyCartTextView);
        share = view.findViewById(R.id.share);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        String retrieveLoggedInUserEmail;
        retrieveLoggedInUserEmail = user.getEmail().toString();
//        db = FirebaseFirestore.getInstance();

//        share.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                String userId = retrieveLoggedInUserEmail;
//                saveCartToFirestore(userId);
//            }
//        });

        updateCartUI();
        return view;
    }

    private void updateCartUI() {
//        List<Product> cartItems = Cart.getInstance().getCartItems();
//        adapter = new CartAdapter(cartItems);
//        recyclerView.setAdapter(adapter);

        List<Product> cartItems = Cart.getInstance().getCartItems();
        if (cartItems.isEmpty()) {
            emptyCartTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyCartTextView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter = new CartAdapter(cartItems);
            recyclerView.setAdapter(adapter);
        }
    }

//    public void saveCartToFirestore(String userId) {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        Cart cart = new Cart();
//        List<Product> cartItems = cart.getCartItems();
//
//        // Reference to the user's cart
//        DocumentReference userCartRef = db.collection("carts").document(userId);
//
//        // Clear existing products
//        userCartRef.collection("products").get().addOnSuccessListener(queryDocumentSnapshots -> {
//            for (DocumentSnapshot doc : queryDocumentSnapshots) {
//                doc.getReference().delete();
//            }
//            // Add updated products
//            for (Product product : cartItems) {
//                Map<String, Object> productData = new HashMap<>();
//                productData.put("name", product.getName());
//                productData.put("quantity", Cart.getInstance().getQuantity(product));
//                productData.put("imageUrl", product.getImageUrl());
//
//                userCartRef.collection("products").document(product.getName())
//                        .set(productData)
//                        .addOnSuccessListener(aVoid -> Log.d("Firestore", "Cart saved successfully!"))
//                        .addOnFailureListener(e -> Log.w("Firestore", "Error saving cart", e));
//            }
//        });
//    }

}
