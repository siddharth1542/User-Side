package com.example.Customer.dashboard_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.Customer.Dashboard;
import com.example.Customer.R;
import com.example.Customer.adapter.Vendor_Adapter;
import com.example.Customer.responseModel.VendorDetailsResponse;
import com.example.Customer.viewModel.VenderViewModel;


public class Fragment_Category_Customer extends Fragment {

    private VenderViewModel venderViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__category__customer, container, false);






        ((Dashboard) getActivity()).getSupportActionBar().setTitle("Category");
        return view;
    }





}