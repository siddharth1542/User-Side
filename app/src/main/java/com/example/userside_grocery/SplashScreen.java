package com.example.Customer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreen extends AppCompatActivity
{

    @BindView(R.id.splash_img)
    ImageView imageView;

    @BindView(R.id.splash_sub_img)
    ImageView sub_imageView;

    Animation top, bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        top= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.top);
        bottom= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom);
        imageView.setAnimation(top);
        sub_imageView.setAnimation(bottom);


        new Timer().schedule(new TimerTask()
        {
            public void run()
            {
                Intent intent = new Intent(SplashScreen.this,Dashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                startActivity(intent);
            }
        }, 2000);

    }
}