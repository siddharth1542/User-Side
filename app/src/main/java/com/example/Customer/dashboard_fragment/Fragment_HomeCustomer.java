package com.example.Customer.dashboard_fragment;

import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Customer.Dashboard;
import com.example.Customer.R;
import com.example.Customer.adapter.Adapter_home;
import com.example.Customer.adapter.ShowMyProductAdapter;
import com.example.Customer.adapter.SlideViewAdapter;
import com.example.Customer.adapter.Vendor_Adapter;
import com.example.Customer.responseModel.AllCategoryDataList;
import com.example.Customer.responseModel.GetCategoryResponseModel;
import com.example.Customer.responseModel.GetProductResponse;
import com.example.Customer.responseModel.VendorDetailsResponse;
import com.example.Customer.responseModel.VendorList;
import com.example.Customer.viewModel.VenderViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_HomeCustomer extends Fragment {


    private VenderViewModel venderViewModel;
    @BindView(R.id.vendor_Recycler)
    RecyclerView rcv_show;

//    @BindView(R.id.product_Recycler)
//    RecyclerView rcv_product;

    @BindView(R.id.category_Recycler)
    RecyclerView rcv_category;

    SliderViewAdapter sliderViewAdapter1,sliderViewAdapter2;

    @BindView(R.id.imageSlider)
    SliderView sliderView;

//    @BindView(R.id.imageSlider2)
//    SliderView sliderView2;

    Vendor_Adapter vendor_adapter;

     Adapter_home adapter_home;
     ShowMyProductAdapter show_product_adapter;


//    @BindView(R.id.tab_category)
//    TabLayout tab_layout;


//    @BindView(R.id.all_vendors)
//    LinearLayout all_vendors;


    Button btnGetLocation;
    TextView showLocation;

    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    double customer_latitude, customer_longitude;

    private ArrayList<AllCategoryDataList> cat_arr;
    private ArrayList<VendorList> SelectVendor;
    private HashMap<String,String> hm_data;

    private HashMap<String,String> shopDataHM;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__home_customer, container, false);
        ButterKnife.bind(this, view);
        venderViewModel = new ViewModelProvider(this).get(VenderViewModel.class);






        getCustomerLocation();
        getVendorlistNoPara();
        get_category();





     //   get_product_List("9");



//        all_vendors.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                String cat="0";
//              getVendorlistNoPara();
//            }
//        });


        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.img_slider);
        images.add(R.drawable.add_image);
        images.add(R.drawable.ic_home);
        images.add(R.drawable.ic_profile);
        images.add(R.drawable.ic_category);

        sliderViewAdapter1 = new SlideViewAdapter(images);
        sliderView.setSliderAdapter(sliderViewAdapter1);
        sliderView.setAutoCycle(true);
        sliderView.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP);



//        List<Integer> images2 = new ArrayList<>();
//        images2.add(R.drawable.img_slider);
//        images2.add(R.drawable.add_image);
//        images2.add(R.drawable.ic_home);
//        images2.add(R.drawable.ic_profile);
//        images2.add(R.drawable.ic_category);
//
//        sliderViewAdapter2 = new SlideViewAdapter(images2);
//        sliderView2.setSliderAdapter(sliderViewAdapter2);
//        sliderView2.setAutoCycle(true);
//        sliderView2.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);
//        sliderView2.setIndicatorAnimation(IndicatorAnimationType.DROP);


        //getdata();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_show.setLayoutManager(gridLayoutManager);


        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(), 3);
        gridLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_category.setLayoutManager(gridLayoutManager1);

//        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 1);
//        gridLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
//        rcv_product.setLayoutManager(gridLayoutManager2);



        ((Dashboard) getActivity()).getSupportActionBar().setTitle("Home");
        return view;
    }



    private void get_category()
    {
        venderViewModel.getMovieList().observe(getViewLifecycleOwner(), new Observer<GetCategoryResponseModel>()
        {
            @Override
            public void onChanged(GetCategoryResponseModel getCategoryResponseModel)
            {

                if (getCategoryResponseModel != null)
                {
                    adapter_home = new Adapter_home(getCategoryResponseModel.getData(),getContext());
                    adapter_home.notifyDataSetChanged();
                    rcv_category.setAdapter(adapter_home);

                }

            }
        });
    }


//    private void get_product_List(String sub_cat_id) {
//
//        String vendor_Id = "14";
//
//        venderViewModel.get_Product("14", sub_cat_id, "0").observe(getViewLifecycleOwner(), new Observer<GetProductResponse>() {
//            @Override
//            public void onChanged(GetProductResponse getProductResponse) {
//                if (getProductResponse != null) {
//                    if (rcv_product.getVisibility() == View.INVISIBLE) {
//                        rcv_product.setVisibility(View.VISIBLE);
//                        show_product_adapter = new ShowMyProductAdapter(getProductResponse.getData(), getContext(), venderViewModel, Integer.parseInt(vendor_Id));
//                        show_product_adapter.notifyDataSetChanged();
//                        rcv_product.setAdapter(show_product_adapter);
//                    } else {
//                        show_product_adapter = new ShowMyProductAdapter(getProductResponse.getData(), getContext(), venderViewModel, Integer.parseInt(vendor_Id));
//                        show_product_adapter.notifyDataSetChanged();
//                        rcv_product.setAdapter(show_product_adapter);
//
//                    }
//                } else {
//                    Toast.makeText(getActivity(), "List Empty!!", Toast.LENGTH_SHORT).show();
//                    rcv_product.setVisibility(View.INVISIBLE);
//                }
//            }
//        });
//
//    }





















//
//
//
//    //  private ArrayList<VendorList> arr;
//
//
////    private void getVendorlist(String catid)
////    {
////        String pageindex = "0";
////        venderViewModel.get_shortvendorDetails(catid,pageindex).observe(getViewLifecycleOwner(), new Observer<VendorDetailsResponse>() {
////            @Override
////            public void onChanged(VendorDetailsResponse vendorDetailsResponse)
////            {
////
////                if (vendorDetailsResponse != null)
////                {
////                    if (rcv_show.getVisibility() == View.INVISIBLE)
////                    {
////                        rcv_show.setVisibility(View.VISIBLE);
////                        vendor_adapter = new Vendor_Adapter(vendorDetailsResponse.getData(),getContext());
////                        vendor_adapter.notifyDataSetChanged();
////                        rcv_show.setAdapter(vendor_adapter);
////                    } else
////                        {
////                        vendor_adapter = new Vendor_Adapter(vendorDetailsResponse.getData(),getContext());
////                        vendor_adapter.notifyDataSetChanged();
////                        rcv_show.setAdapter(vendor_adapter);
////
////                    }
////                } else {
////                    Toast.makeText(getActivity(), "List Empty!!", Toast.LENGTH_SHORT).show();
////                    rcv_show.setVisibility(View.INVISIBLE);
////                }
//////                arr.addAll(vendorDetailsResponse.getData());
////
////            for (int i = 0; i < vendorDetailsResponse.getData().size(); i++)
////                {
////                    //latitudee 25
////                    //longitude 82
////
////                    double vendor_latitude = Double.parseDouble(vendorDetailsResponse.getData().get(i).getLatitude());
////                    double vendor_longitude = Double.parseDouble(vendorDetailsResponse.getData().get(i).getLongitude());
////
////                    double earthRadius = 3958.75;
////                    double dLat = Math.toRadians(vendor_latitude-customer_latitude);
////                    double dLng = Math.toRadians(vendor_longitude-customer_longitude);
////                    double a = Math.sin(dLat/2) *
////                               Math.sin(dLat/2) +
////                               Math.cos(Math.toRadians(customer_latitude)) *
////                               Math.cos(Math.toRadians(vendor_latitude)) *
////                               Math.sin(dLng/2) *
////                               Math.sin(dLng/2);
////                    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
////                    double dist = earthRadius * c;
////
//////                    if (dist < 4)
//////                    {
////                        SelectVendor.addAll(vendorDetailsResponse.getData());
////     //               }
////                }
////                    if(SelectVendor.isEmpty())
////                    {
////                        Toast.makeText(getActivity(), "No Vendors", Toast.LENGTH_SHORT).show();
////                    }else
////                    {
////                        vendor_adapter = new Vendor_Adapter(vendorDetailsResponse.getData(), getContext());
////                        rcv_show.setAdapter(vendor_adapter);
////                    }
//
//
//
//
////                vendor_adapter = new Vendor_Adapter(vendorDetailsResponse.getData(), getContext());
////                rcv_show.setAdapter(vendor_adapter);
//
//
//
//        //
////        });
////
//   }


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



////                arr.addAll(vendorDetailsResponse.getData());
//
//            for (int i = 0; i < vendorDetailsResponse.getData().size(); i++)
//                {
//                    //latitudee 25
//                    //longitude 82
//
//                    double vendor_latitude = Double.parseDouble(vendorDetailsResponse.getData().get(i).getLatitude());
//                    double vendor_longitude = Double.parseDouble(vendorDetailsResponse.getData().get(i).getLongitude());
//
//                    double earthRadius = 3958.75;
//                    double dLat = Math.toRadians(vendor_latitude-customer_latitude);
//                    double dLng = Math.toRadians(vendor_longitude-customer_longitude);
//                    double a = Math.sin(dLat/2) *
//                               Math.sin(dLat/2) +
//                               Math.cos(Math.toRadians(customer_latitude)) *
//                               Math.cos(Math.toRadians(vendor_latitude)) *
//                               Math.sin(dLng/2) *
//                               Math.sin(dLng/2);
//                    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
//                    double dist = earthRadius * c;
//
////                    if (dist < 4)
////                    {
//                        SelectVendor.addAll(vendorDetailsResponse.getData());
//     //               }
//                }
//                    if(SelectVendor.isEmpty())
//                    {
//                        Toast.makeText(getActivity(), "No Vendors", Toast.LENGTH_SHORT).show();
//                    }else
//                    {
//                        vendor_adapter = new Vendor_Adapter(vendorDetailsResponse.getData(), getContext());
//                        rcv_show.setAdapter(vendor_adapter);
//                    }




//                vendor_adapter = new Vendor_Adapter(vendorDetailsResponse.getData(), getContext());
//                rcv_show.setAdapter(vendor_adapter);




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