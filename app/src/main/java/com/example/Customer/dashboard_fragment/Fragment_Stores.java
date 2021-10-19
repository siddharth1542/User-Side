package com.example.Customer.dashboard_fragment;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.Customer.Dashboard;
import com.example.Customer.R;
import com.example.Customer.adapter.Adapter_home;
import com.example.Customer.adapter.Vendor_Adapter;
import com.example.Customer.responseModel.VendorDetailsResponse;
import com.example.Customer.responseModel.VendorList;
import com.example.Customer.viewModel.VenderViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Fragment_Stores extends Fragment
{

    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    double customer_latitude, customer_longitude;



    Vendor_Adapter vendor_adapter;

    Adapter_home adapter_home;
    private ArrayList<VendorList> SelectVendor;
    private HashMap<String,String> hm_data;
    private HashMap<String,String> shopDataHM;


    private VenderViewModel venderViewModel;
    @BindView(R.id.vendor_Recycler)
    RecyclerView rcv_show;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment__stores, container, false);
        ButterKnife.bind(this,view);
        venderViewModel = new ViewModelProvider(this).get(VenderViewModel.class);


        getCustomerLocation();
        getVendorlistNoPara();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_show.setLayoutManager(gridLayoutManager);



        ((Dashboard) getActivity()).getSupportActionBar().setTitle("Stores");
      return view;
    }


    private void getVendorlistNoPara()
    {
        String pageindex = "0";
        venderViewModel.get_shortvendorDetailsNoPara(pageindex).observe(getViewLifecycleOwner(), new Observer<VendorDetailsResponse>() {
            @Override
            public void onChanged(VendorDetailsResponse vendorDetailsResponse)
            {
                List<VendorList> demoArr = new ArrayList<>();
                shopDataHM = new HashMap<>();

                if (vendorDetailsResponse != null)
                {
                    if (rcv_show.getVisibility() == View.INVISIBLE)
                    {
                        rcv_show.setVisibility(View.VISIBLE);

                        for (int i = 0; i < vendorDetailsResponse.getData().size(); i++)
                        {
                            double vendor_latitude = Double.parseDouble(vendorDetailsResponse.getData().get(i).getLatitude());
                            double vendor_longitude = Double.parseDouble(vendorDetailsResponse.getData().get(i).getLongitude());

                            double earthRadius = 3958.75;
                            double dLat = Math.toRadians(vendor_latitude-customer_latitude);
                            double dLng = Math.toRadians(vendor_longitude-customer_longitude);
                            double a = Math.sin(dLat/2) *
                                    Math.sin(dLat/2) +
                                    Math.cos(Math.toRadians(customer_latitude)) *
                                            Math.cos(Math.toRadians(vendor_latitude)) *
                                            Math.sin(dLng/2) *
                                            Math.sin(dLng/2);
                            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
                            double dist = earthRadius * c;

                            if (dist<4000)
                            {
                                demoArr.add(vendorDetailsResponse.getData().get(i));
                            }

                        }
                        vendor_adapter = new Vendor_Adapter(vendorDetailsResponse.getData(),getContext());
                        vendor_adapter.notifyDataSetChanged();
                        rcv_show.setAdapter(vendor_adapter);


//                        vendor_adapter = new Vendor_Adapter(vendorDetailsResponse.getData(),getContext());
//                        vendor_adapter.notifyDataSetChanged();
//                        rcv_show.setAdapter(vendor_adapter);
                    } else
                    {
                        vendor_adapter = new Vendor_Adapter(vendorDetailsResponse.getData(),getContext());
                        vendor_adapter.notifyDataSetChanged();
                        rcv_show.setAdapter(vendor_adapter);

                    }
                } else {
                    Toast.makeText(getActivity(), "List Empty!!", Toast.LENGTH_SHORT).show();
                    rcv_show.setVisibility(View.INVISIBLE);
                }

            }
        });

    }





    private void getCustomerLocation()
    {
        locationManager = (LocationManager) getActivity().getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            getLocation();
        }
    }

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getLocation()
    {
        if (ActivityCompat.checkSelfPermission(
                getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                customer_latitude = lat;
                customer_longitude = longi;
                //     Toast.makeText(getActivity(), "Latitude"+customer_latitude+"\n"+customer_longitude, Toast.LENGTH_SHORT).show();
                //  showLocation.setText("Your Location: " + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude);
            } else {
                Toast.makeText(getActivity(), "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}