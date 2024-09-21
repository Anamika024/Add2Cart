package com.godspeed.gameschhalaang.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.godspeed.gameschhalaang.product.adapter.ProductAdapter;
import com.godspeed.gameschhalaang.product.model.Product;
import com.weforwins.chhalaang.R;

import java.util.ArrayList;
import java.util.List;

public class ProductDashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private Spinner categorySpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_dashboard, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        categorySpinner = view.findViewById(R.id.categorySpinner);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ProductAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // Set up the spinner with categories
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item,
                new String[]{"Mobile Plans", "Broadcast Plans", "Telecom Services"});
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerAdapter);

        // Handle category selection
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // Mobile Plans
                        updateRecyclerView(getMobilePlans());
                        break;
                    case 1: // Broadcast Plans
                        updateRecyclerView(getBroadcastPlans());
                        break;
                    case 2: // Telecom Services
                        updateRecyclerView(getTelecomServices());
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Load default data on fragment creation
        if (savedInstanceState == null) {
            categorySpinner.setSelection(0); // Set default to Mobile Plans
        }

        return view;
    }

    // Method to update RecyclerView with selected data
    private void updateRecyclerView(List<Product> products) {
        adapter.updateData(products);
    }

    // Dummy data for Mobile Plans
    private List<Product> getMobilePlans() {
        List<Product> mobilePlans = new ArrayList<>();
        mobilePlans.add(new Product("Unlimited 5G Plan", "Unlimited talk, text, and data", 69.99, R.drawable.mobileplans));
        mobilePlans.add(new Product("Prepaid 10GB Plan", "10GB of high-speed data", 29.99, R.drawable.mobileplans));
        return mobilePlans;
    }

    // Dummy data for Broadcast Plans
    private List<Product> getBroadcastPlans() {
        List<Product> broadcastPlans = new ArrayList<>();
        broadcastPlans.add(new Product("Premium HD Channels Package", "150+ HD channels", 59.99, R.drawable.broadcast));
        broadcastPlans.add(new Product("Basic Cable Package", "50+ channels", 29.99, R.drawable.broadcast));
        return broadcastPlans;
    }

    // Dummy data for Telecom Services
    private List<Product> getTelecomServices() {
        List<Product> telecomServices = new ArrayList<>();
        telecomServices.add(new Product("Home Internet 500Mbps", "500Mbps download/upload", 49.99, R.drawable.broadcast));
        telecomServices.add(new Product("Fiber Optic 1Gbps", "1Gbps speed", 79.99, R.drawable.broadcast));
        return telecomServices;
    }
}


