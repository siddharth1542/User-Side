package com.example.Customer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Logout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);


        new Timer().schedule(new TimerTask()
        {
            public void run()
            {

                SharedPreferences sharedPreferences =getSharedPreferences("VENDOR_DATA",MODE_PRIVATE);
                sharedPreferences.edit().remove("MOBILE").commit();
                sharedPreferences.edit().remove("SHOPNAME").commit();
                sharedPreferences.edit().remove("CATEGORY_ID").commit();
                sharedPreferences.edit().remove("CATEGORYNAME").commit();
                sharedPreferences.edit().remove("VENDOR_ID").commit();
                sharedPreferences.edit().remove("IPADDRESS").commit();
          //      finish();

                Intent intent = new Intent(getApplicationContext(), LoginAndRegistration.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        }, 1500);
    }
}