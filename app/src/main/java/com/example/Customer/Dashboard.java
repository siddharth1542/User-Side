package com.example.Customer;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.Customer.dashboard_fragment.Fragment_Bank;
import com.example.Customer.dashboard_fragment.Fragment_Blank;
import com.example.Customer.dashboard_fragment.Fragment_Category_Customer;
import com.example.Customer.dashboard_fragment.Fragment_Customer_Profile;
import com.example.Customer.dashboard_fragment.Fragment_HomeCustomer;
import com.example.Customer.dashboard_fragment.Fragment_KYC;
import com.example.Customer.dashboard_fragment.Fragment_Search;
import com.example.Customer.responseModel.AllCategoryDataList;
import com.example.Customer.viewModel.VenderViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class Dashboard extends AppCompatActivity
{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    VenderViewModel myViewModel;

    LocationRequest mLocationRequest;
    ActionBarDrawerToggle toggle;
    Location currentLocation;
    LocationCallback mLocationCallback;

    List<AllCategoryDataList> data_category;

    FusedLocationProviderClient fusedLocationProviderClient;

    LocationManager lm;
    BottomNavigationView bottomNavigationView;
    int login = 1;
    String vendorId="",cat_Id="",mobile_no="",shopName="",email="12",IPAddress="",category_name="";




    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        toolbar = findViewById(R.id.nav_toolbar);

        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.app_white));





        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawerLayout);


        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {


                    case R.id.ProductRequest:

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new Fragment_HomeCustomer()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.Refer:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.BankDetails:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new Fragment_Bank()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.KYCDetails:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new Fragment_KYC()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.logout:
                          Intent i = new Intent(Dashboard.this,Logout.class);
                          startActivity(i);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });


        myViewModel = new ViewModelProvider(this).get(VenderViewModel.class);

        bottomNavigationView = findViewById(R.id.dash_bottom_navigate);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListner);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Fragment_HomeCustomer()).commit();


        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                currentLocation = locationResult.getLastLocation();

            }
        };

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {

                        case R.id.Home:
                            selectedFragment = new Fragment_HomeCustomer();
                            break;

                        case R.id.Store:
                            selectedFragment = new Fragment_Search();
                            break;

                        case R.id.Category:
                            selectedFragment = new Fragment_Category_Customer();
                            break;

                        case R.id.Cart:
                            selectedFragment = new Fragment_Blank();
                            break;

                        case R.id.profile:
                            selectedFragment = new Fragment_Customer_Profile();
                            break;


                    }

                   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                           selectedFragment).commit();


                    return true;
                }

            };

}