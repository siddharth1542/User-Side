package com.example.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckInternet extends AppCompatActivity {

    @BindView(R.id.check_internet)
    Button checkInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_internet);

        ButterKnife.bind(this);

        checkInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(CheckInternet.this, Dashboard.class);
                startActivity(intent);
            }
        });

    }
}