package com.example.Customer.dashboard_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.Customer.Dashboard;
import com.example.Customer.R;


public class BankandKYCdetails extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ((Dashboard) getActivity()).getSupportActionBar().setTitle("KYC");
        return inflater.inflate(R.layout.fragment_bankand_k_y_cdetails, container, false);
    }
}