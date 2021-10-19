package com.example.Customer.dashboard_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Customer.Dashboard;
import com.example.Customer.R;

public class Fragment_Customer_Profile extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment__customer__profile, container, false);





        ((Dashboard) getActivity()).getSupportActionBar().setTitle("Profile");
        return view;
    }
}