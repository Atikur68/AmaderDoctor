package com.atikur.amaderdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import yanzhikai.textpath.TextPathView;

public class SplashActivity extends AppCompatActivity {
    private TextPathView mPathView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mPathView = findViewById(R.id.text_pathView);
        mPathView.startAnimation(0, 1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 4000);
    }


}
