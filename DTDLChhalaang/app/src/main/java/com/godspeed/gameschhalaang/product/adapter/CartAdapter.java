package com.godspeed.gameschhalaang.product.adapter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.godspeed.gameschhalaang.product.model.Cart;
import com.godspeed.gameschhalaang.product.model.Product;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.weforwins.chhalaang.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<Product> cartItems;

    public CartAdapter(List<Product> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (cartItems.isEmpty()) {
            Log.i("CartAdapter", "empty");
            holder.emptyCart.setVisibility(View.VISIBLE);
//            holder.cartItemsContainer.setVisibility(View.GONE);
        } else {
            holder.emptyCart.setVisibility(View.GONE);
//            holder.cartItemsContainer.setVisibility(View.VISIBLE);

            Product product = cartItems.get(position);
            int quantity = Cart.getInstance().getQuantity(product);

            holder.productName.setText(product.getName());
            holder.productQuantity.setText("Quantity: " + quantity);

            Glide.with(holder.itemView.getContext())
                    .load(product.getImageUrl())
                    .into(holder.productImage);

            holder.flagButton.setOnClickListener(v -> {
                holder.suggestItems.setVisibility(View.VISIBLE);
                holder.suggestedPlanTextView.setVisibility(View.VISIBLE);
                holder.suggestedPlanTextView.setText("flagged");
            });

            holder.removeButton.setOnClickListener(v -> {
                Cart.getInstance().removeProduct(product);
                cartItems.remove(position);
                notifyItemRemoved(position);

                if (cartItems.isEmpty()) {
                    holder.emptyCart.setVisibility(View.VISIBLE);
//                    holder.cartItemsContainer.setVisibility(View.GONE);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productName, productQuantity, suggestedPlanTextView;
        public ImageButton removeButton, flagButton;
        public ImageView productImage;
        public TextView emptyCart;
        public LinearLayout cartItemsContainer;
        public Spinner suggestItems;

        public ViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productQuantity = itemView.findViewById(R.id.productQuantity);
            removeButton = itemView.findViewById(R.id.removeButton);
            emptyCart = itemView.findViewById(R.id.emptyCartTextView);
            flagButton = itemView.findViewById(R.id.flagButton);
            suggestItems = itemView.findViewById(R.id.suggestItems);
            suggestedPlanTextView = itemView.findViewById(R.id.suggestedPlanTextView);
        }
    }
}

