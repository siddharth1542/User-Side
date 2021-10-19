package com.example.Customer.dashboard_fragment;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.Customer.BuildConfig;
import com.example.Customer.Dashboard;
import com.example.Customer.R;
import com.example.Customer.responseModel.Add_Delivery_Response_Model;
import com.example.Customer.responseModel.GetVendorById;
import com.example.Customer.responseModel.GetVendorProfileResponse;
import com.example.Customer.viewModel.VenderViewModel;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Fragment_Profile extends Fragment {


    @BindView(R.id.start_time)
    TextView start_time;

    @BindView(R.id.End_time)
    TextView end_time;

    @BindView(R.id.time_error)
    EditText time_error;

    @BindView(R.id.checkbox_error)
    TextView checkbox_error;


    @BindView(R.id.Start_Time_Card)
    CardView select_start;

    @BindView(R.id.Close_Time_Card)
    CardView select_close;

    @BindView(R.id.txt_latitude)
    TextView txt_latitude;

    @BindView(R.id.txt_longitute)
    TextView txt_longitude;

    @BindView(R.id.add_delivery_charge)
    TextView txt_delivercharge;


    @BindView(R.id.cashOnDelivery)
    CheckBox cashOnDelivery;

    @BindView(R.id.onlinePayment)
    CheckBox onlinePayment;


    @BindView(R.id.profile_save)
    Button profile_save;



    @BindView(R.id.edt_shopName)
    EditText edt_shopName;

    @BindView(R.id.edt_email)
    EditText edt_email;

    @BindView(R.id.edt_buildingName)
    EditText edt_buildingName;

    @BindView(R.id.edt_roadName)
    EditText edt_roadName;

    @BindView(R.id.edt_landMark)
    EditText edt_landMark;

    @BindView(R.id.edt_city)
    EditText edt_city;

    @BindView(R.id.edt_state)
    EditText edt_state;

    @BindView(R.id.edt_pincode)
    EditText edt_pincode;

    @BindView(R.id.edt_ownerName)
    EditText edt_ownerName;

    @BindView(R.id.edt_whatsappNumber)
    EditText edt_whatsappNumber;

    ProgressBar progressBar;


    RadioButton selectRadioButton;
    List<GetVendorById> vendor_list;

    Dialog dialog;

    int VendorId,Pincode;
    String Name,Email,Longitude,Latitude,OwnerName,WhatsappNumber, Address,IPAddress;
    String OpenTime;
    String  CloseTime;
    boolean CashOnDelivery = false, OnlinePayment = false;
    int check_cash=0;

    String check_online,check_cash1;

    String BuildingName, RoadName, LandMark, City, State1;
    VenderViewModel venderViewModel;
    TimePicker timePicker1;
    Calendar calendar;
    String format = "";

    private int count = 1;



    // location last updated time
    private String mLastUpdateTime;

    // location updates interval - 10sec
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    // fastest updates interval - 5 sec
    // location updates will be received if another app is requesting the locations
    // than your app can handle
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 5000;

    private static final int REQUEST_CHECK_SETTINGS = 100;

    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;






    // boolean flag to toggle the ui
    private Boolean mRequestingLocationUpdates;





    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this,view);


        SharedPreferences sharedPreferences = getContext().getSharedPreferences("VENDOR_DATA", Context.MODE_PRIVATE);
        VendorId = Integer.parseInt(sharedPreferences.getString("VENDOR_ID",null));

        String temp_vendor = String.valueOf(VendorId);

        venderViewModel = new ViewModelProvider(this).get(VenderViewModel.class);
         get_vendor_profile(temp_vendor);


        // initialize the necessary libraries
        init();
        // restore the values from saved instance state
        restoreValuesFromBundle(savedInstanceState);

        startLocationButtonClick();





        select_start.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View view)
            {
                time_error.setVisibility(INVISIBLE);
            openDialogue();
            }
        });
        select_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                time_error.setVisibility(INVISIBLE);
                openDialogueClose();
            }
        });


                txt_delivercharge.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        openPop();

                    }
                });





        profile_save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if (edt_shopName.getText().toString().isEmpty())
                {
                    edt_shopName.setError("Please Enter Shop Name");
                    edt_shopName.requestFocus();
                    return;
                }
                Name = edt_shopName.getText().toString();


                String emailInput = edt_email.getText().toString().trim();
              //  String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if(!emailInput.matches(emailPattern))
                {
                    edt_email.setError("Invalid email address");
                    edt_email.requestFocus();
                    return;
                }
                Email = edt_email.getText().toString();



                if (edt_ownerName.getText().toString().isEmpty())
                {
                    edt_ownerName.setError("Please Enter Owner Name");
                    edt_ownerName.requestFocus();
                    return;
                }
                OwnerName = edt_ownerName.getText().toString();

                if (edt_whatsappNumber.getText().toString().isEmpty())
                {
                    edt_whatsappNumber.setError("Please Enter WhatsApp Number");
                    edt_whatsappNumber.requestFocus();
                    return;
                }
                WhatsappNumber = edt_whatsappNumber.getText().toString();


                if (start_time.getText().toString().isEmpty())
                {
                   time_error.setVisibility(VISIBLE);
                   time_error.requestFocus();
                    return;
                }
                OpenTime =  start_time.getText().toString()+":54.2600000";


                if (end_time.getText().toString().isEmpty())
                {
                    time_error.setVisibility(VISIBLE);
                    time_error.requestFocus();
                    return;
                }
                CloseTime = end_time.getText().toString()+":54.2600000";

              //  "10:17:54.2600000"

                if (edt_pincode.getText().toString().isEmpty())
                {
                    edt_pincode.setError("Please Enter Pincode");
                    edt_pincode.requestFocus();
                    return;
                }
                Pincode = Integer.parseInt(edt_pincode.getText().toString());

                if (edt_buildingName.getText().toString().isEmpty())
                {
                    edt_buildingName.setError("Please Enter Shopno. or Building Name");
                    edt_buildingName.requestFocus();
                    return;
                }
                BuildingName = edt_buildingName.getText().toString();

                if (edt_roadName.getText().toString().isEmpty())
                {
                    edt_roadName.setError("Please Enter Road Name or Society Name");
                    edt_roadName.requestFocus();
                    return;
                }
                RoadName = edt_roadName.getText().toString();




                if (edt_landMark.getText().toString().isEmpty())
                {
                    edt_landMark.setError("Please Enter Landmark");
                    edt_landMark.requestFocus();
                    return;
                }
                LandMark = edt_landMark.getText().toString();

                if (edt_city.getText().toString().isEmpty())
                {
                    edt_city.setError("Please Enter Your City");
                    edt_city.requestFocus();
                    return;
                }
                City = edt_city.getText().toString();

                if (edt_state.getText().toString().isEmpty())
                {
                    edt_state.setError("Please Enter Your State");
                    edt_state.requestFocus();
                    return;
                }
                State1 = edt_state.getText().toString();

                if(!cashOnDelivery.isChecked())
                {
                     if(!onlinePayment.isChecked())
                     {
                         checkbox_error.setVisibility(VISIBLE);
                         checkbox_error.requestFocus();
                         return;
                     }
                }


                if (cashOnDelivery.isChecked())
                {
                    CashOnDelivery = true;
                    checkbox_error.setVisibility(INVISIBLE);
                }else if (!cashOnDelivery.isChecked()) {
                    CashOnDelivery = false;
                }
                if (onlinePayment.isChecked())
                {
                    OnlinePayment = true;
                    checkbox_error.setVisibility(INVISIBLE);
                }
                else if (!onlinePayment.isChecked())
                {
                    OnlinePayment = false;
                }



                Address = Name+","+BuildingName+","+RoadName+","+LandMark+","+City+","+State1;

//                @SuppressLint("WifiManagerLeak")
//                WifiManager wifiManager = (WifiManager) getContext().getSystemService(WIFI_SERVICE);
//                IPAddress= Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
                  IPAddress = getIPAddress(true);


           String s =  venderViewModel.post_profile(VendorId,Name,Email,OpenTime,CloseTime,CashOnDelivery,OnlinePayment,Longitude,Latitude,OwnerName,WhatsappNumber,Address,Pincode,IPAddress);

                SharedPreferences.Editor edtr = sharedPreferences.edit();
                edtr.putString("SHOPNAME",Name);
                edtr.putString("OPENTIME",start_time.getText().toString());
                edtr.putString("CLOSETIME",end_time.getText().toString());
                edtr.commit();
                edtr.apply();




     //      Toast.makeText(getActivity(), "Vendor info successfully updated", Toast.LENGTH_SHORT).show();

            }
        });

        ((Dashboard) getActivity()).getSupportActionBar().setTitle("Profile");
        return view;
    }



//    =========================================End OnCreateView=======================================


    private void openPop() {

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.add_delivery_pop);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        Window window = dialog.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_MaterialComponents_BottomSheetDialog;




        dialog.show();

        TextView Add_distance = dialog.findViewById(R.id.pop_add_distance);
        TextView Add_charges = dialog.findViewById(R.id.pop_add_delivery_charge);
        Button save_charge = dialog.findViewById(R.id.pop_add_charges_btnSave);
        ImageView img_clear = dialog.findViewById(R.id.img_clear);

        img_clear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dialog.dismiss();
            }
        });



        save_charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = requireContext().getSharedPreferences("VENDOR_DATA",Context.MODE_PRIVATE);

                String v_id = sharedPreferences.getString("VENDOR_ID",null);


                String dis = Add_distance.getText().toString();
                String charge = Add_charges.getText().toString();
                String iPAddress  =  sharedPreferences.getString("IPADDRESS",null);
                venderViewModel.add_delivery_charges(v_id,Float.parseFloat(dis),Float.parseFloat(charge),iPAddress).observe(getViewLifecycleOwner(), new Observer<Add_Delivery_Response_Model>() {
                    @Override
                    public void onChanged(Add_Delivery_Response_Model add_delivery_response_model) {


                        Toast.makeText(getContext(), ""+add_delivery_response_model.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                });

            }
        });



    }






    public void stopLocationUpdates () {
        // Removing location updates
        mFusedLocationClient
                .removeLocationUpdates(mLocationCallback)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Toast.makeText(getContext(), "Location updates stopped!", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public void onActivityResult ( int requestCode, int resultCode, Intent data){
        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Log.e("TAG", "User agreed to make required location settings changes.");
                        // Nothing to do. startLocationupdates() gets called in onResume again.
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.e("TAG", "User chose not to make required location settings changes.");
                        mRequestingLocationUpdates = false;
                        break;
                }
                break;
        }
    }

    private void openSettings () {
        Intent intent = new Intent();
        intent.setAction(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",
                BuildConfig.APPLICATION_ID, null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @Override
    public void onResume () {
        super.onResume();

        // Resuming location updates depending on button state and
        // allowed permissions
        if (mRequestingLocationUpdates && checkPermissions()) {
            startLocationUpdates();
        }

        updateLocationUI();
    }

    private boolean checkPermissions () {
        int permissionState = ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }


    @Override
    public void onPause () {
        super.onPause();

        if (mRequestingLocationUpdates)
        {
            // pausing location updates
            stopLocationUpdates();
        }
    }


    private void restoreValuesFromBundle (Bundle savedInstanceState){

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("is_requesting_updates")) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean("is_requesting_updates");
            }

            if (savedInstanceState.containsKey("last_known_location")) {
                mCurrentLocation = savedInstanceState.getParcelable("last_known_location");
            }

            if (savedInstanceState.containsKey("last_updated_on")) {
                mLastUpdateTime = savedInstanceState.getString("last_updated_on");
            }
        }

        updateLocationUI();


    }

    private void init () {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
        mSettingsClient = LocationServices.getSettingsClient(getContext());

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                // location is received
                mCurrentLocation = locationResult.getLastLocation();
                mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());

                updateLocationUI();
            }
        };

        mRequestingLocationUpdates = false;
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
    }


    private void updateLocationUI () {
        if (mCurrentLocation != null) {


            List<android.location.Address> addressList = new ArrayList<>();
            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
            try {
                addressList = geocoder.getFromLocation(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude(), 1);

            } catch (IOException e) {
                e.printStackTrace();
            }


            edt_state.setText(addressList.get(0).getAdminArea());
            edt_pincode.setText(addressList.get(0).getPostalCode());
           // edt_city.setText(addressList.get(0).getLocality());

            txt_latitude.setText(mCurrentLocation.getLatitude() + "");
            txt_longitude.setText(mCurrentLocation.getLongitude() + "");

            Latitude = txt_latitude.getText().toString();
            Longitude = txt_longitude.getText().toString();
        }

    }

    @Override
    public void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean("is_requesting_updates", mRequestingLocationUpdates);
        outState.putParcelable("last_known_location", mCurrentLocation);
        outState.putString("last_updated_on", mLastUpdateTime);

    }


    private void startLocationUpdates () {

        mSettingsClient
                .checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(getActivity(), new OnSuccessListener<LocationSettingsResponse>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        Log.i("TAG", "All location settings are satisfied.");


                        // Toast.makeText(getContext(), "Started location updates!", Toast.LENGTH_SHORT).show();

                        //noinspection MissingPermission
                        mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                                mLocationCallback, Looper.myLooper());

                        updateLocationUI();
                    }
                })
                .addOnFailureListener(getActivity(), new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                Log.i("TAG", "Location settings are not satisfied. Attempting to upgrade " +
                                        "location settings ");
                                try {
                                    // Show the dialog by calling startResolutionForResult(), and check the
                                    // result in onActivityResult().
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(getActivity(), REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.i("TAG", "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "Location settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings.";
                                Log.e("TAG", errorMessage);

                                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                        }

                        updateLocationUI();
                    }
                });
    }



    private void startLocationButtonClick() {


        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        mRequestingLocationUpdates = true;
                        startLocationUpdates();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // open device settings when the permission is
                        // denied permanently
                        openSettings();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }
           ).check();

    }

    private void get_vendor_profile(String temp_vendor)
    {
        venderViewModel.get_profile(temp_vendor).observe(getViewLifecycleOwner(), new Observer<GetVendorProfileResponse>() {
            @Override
            public void onChanged(GetVendorProfileResponse getVendorProfileResponse) {
                if (getVendorProfileResponse != null)
                {
                    edt_shopName.setText(getVendorProfileResponse.getData().get(0).getName());
                    edt_ownerName.setText(getVendorProfileResponse.getData().get(0).getOwnerName());
                    edt_whatsappNumber.setText(getVendorProfileResponse.getData().get(0).getWhatsappNumber());
                    edt_email.setText(getVendorProfileResponse.getData().get(0).getEmail());
                     check_online =getVendorProfileResponse.getData().get(0).getOnlinePayment();
                     check_cash1 = getVendorProfileResponse.getData().get(0).getCashOnDelivery();
//                    start_time.setText(getVendorProfileResponse.getData().get(0).getOpenTime());
//                    end_time.setText(getVendorProfileResponse.getData().get(0).getCloseTime());

                  if(check_online == "true")
                    {
                        onlinePayment.setChecked(true);
                        OnlinePayment = true;
                    }

                    if(check_cash1 == "true")
                    {
                        cashOnDelivery.setChecked(true);
                        CashOnDelivery = true;
                    }

                    String temp_address = getVendorProfileResponse.getData().get(0).getAddress();
                    if (temp_address.isEmpty())
                    {

                    }else{
                        List<String> seprate_list = Arrays.asList(temp_address.split(","));
                        edt_buildingName.setText(seprate_list.get(1));
                        edt_roadName.setText(seprate_list.get(2));
                        edt_landMark.setText(seprate_list.get(3));
                        edt_city.setText(seprate_list.get(4));

                    }



                    String temp_open_time = getVendorProfileResponse.getData().get(0).getOpenTime();
                    if (temp_open_time.isEmpty())
                    {

                    }else
                        {
                            List<String> seprate_open_time = Arrays.asList(temp_open_time.split(":"));
                            start_time.setText(seprate_open_time.get(0)+":"+seprate_open_time.get(1));
                        }

                    String temp_close_time = getVendorProfileResponse.getData().get(0).getCloseTime();
                    if (temp_close_time.isEmpty())
                    {

                    }else
                    {
                        List<String> seprate_close_time = Arrays.asList(temp_close_time.split(":"));
                        end_time.setText(seprate_close_time.get(0)+":"+seprate_close_time.get(1));
                    }




                  //  21:00:54.2600000
                }
            }
        });

    }

//==================Dialouge Box============================
    private void openDialogue1() {

        dialog = new Dialog(dialog.getContext());

        dialog.setContentView(R.layout.verified);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();


        final Handler handler  = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        };

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, 2000);

    }


//    ======================================IPAddress==================================
    public static String getIPAddress(boolean useIPv4) {
        try {
            for (final Enumeration<NetworkInterface> enumerationNetworkInterface = NetworkInterface.getNetworkInterfaces(); enumerationNetworkInterface.hasMoreElements();) {
                final NetworkInterface networkInterface = enumerationNetworkInterface.nextElement();
                for (Enumeration<InetAddress> enumerationInetAddress = networkInterface.getInetAddresses(); enumerationInetAddress.hasMoreElements();) {
                    final InetAddress inetAddress = enumerationInetAddress.nextElement();
                    final String ipAddress = inetAddress.getHostAddress();
                    if(!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return ipAddress;
                    }
                }
            }
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }





    //=========================Open Time================================
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void openDialogue()
    {

      Button save;

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.activity_time_picker);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCanceledOnTouchOutside(true);

//        ==================================================Time====================================================
        save = dialog.findViewById(R.id.set_button);
        timePicker1 = dialog.findViewById(R.id.timePicker1);
        calendar = Calendar.getInstance();

        timePicker1.setIs24HourView(true);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
       // showTime(hour, min);

        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                setTime(view);
                dialog.dismiss();
            }
        });
//        ==================================================Time====================================================
//
        dialog.show();

//
//        final Handler handler  = new Handler();
//        final Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                if (dialog.isShowing()) {
//                    dialog.dismiss();
//                }
//            }
//        };


    }
    public void setTime(View view)
    {
        int hour = timePicker1.getCurrentHour();
        int min = timePicker1.getCurrentMinute();
        showTime(hour, min);

    }

    public void showTime(int hour, int min)
    {
//        if (hour == 0) {
//            hour += 12;
//            format = "AM";
//        } else if (hour == 12) {
//            format = "PM";
//        } else if (hour > 12) {
//            hour -= 12;
//            format = "PM";
//        } else {
//            format = "AM";
//        }

        if(hour<10)
        {
            start_time.setText(new StringBuilder().append("0").append(hour).append(":").append(min));
            if(min<10)
            {
                start_time.setText(new StringBuilder().append("0").append(hour).append(":").append("0").append(min));
            }
        }
        if (min<10)
        {
            start_time.setText(new StringBuilder().append(hour).append(":").append("0").append(min));
            if(hour<10)
            {
                start_time.setText(new StringBuilder().append("0").append(hour).append(":").append("0").append(min));
            }
        }
        if (hour>9)
        {
            if (min<10)
            {
                start_time.setText(new StringBuilder().append(hour).append(":").append("0").append(min));
            }
            else {
                start_time.setText(new StringBuilder().append(hour).append(":").append(min));
            }
        }


    }
//=========================Close Time================================


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void openDialogueClose()

        {

        Button save;

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.activity_time_picker);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCanceledOnTouchOutside(true);

//        ==================================================Time====================================================
        save = dialog.findViewById(R.id.set_button);
        timePicker1 = dialog.findViewById(R.id.timePicker1);
            timePicker1.setIs24HourView(true);
        calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        //showTimeClose(hour, min);

        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                setTimeClose(view);
                dialog.dismiss();

            }
        });
//        ==================================================Time====================================================
//
        dialog.show();


        final Handler handler  = new Handler();
        final Runnable runnable = new Runnable()
        {
            @Override
            public void run() {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        };
     }

    public void setTimeClose(View view)
    {
        int hour = timePicker1.getCurrentHour();
        int min = timePicker1.getCurrentMinute();
        showTimeClose(hour, min);

    }

    public void showTimeClose(int hour, int min)
    {
//        if (hour == 0) {
//            hour += 12;
//            format = "AM";
//        } else if (hour == 12) {
//            format = "PM";
//        } else if (hour > 12) {
//            hour -= 12;
//            format = "PM";
//        } else {
//            format = "AM";
//        }

        if(hour<10)
        {
            end_time.setText(new StringBuilder().append("0").append(hour).append(":").append(min));
            if(min<10)
            {
                end_time.setText(new StringBuilder().append("0").append(hour).append(":").append("0").append(min));
            }
        }
        if (min<10)
        {
            end_time.setText(new StringBuilder().append(hour).append(":").append("0").append(min));
            if(hour<10)
            {
                end_time.setText(new StringBuilder().append("0").append(hour).append(":").append("0").append(min));
            }
        }
        if (hour>9)
        {
            if (min<10)
            {
                end_time.setText(new StringBuilder().append(hour).append(":").append("0").append(min));
            }
            else {
                end_time.setText(new StringBuilder().append(hour).append(":").append(min));
            }
        }





    }


}