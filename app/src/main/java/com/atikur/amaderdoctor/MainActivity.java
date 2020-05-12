package com.atikur.amaderdoctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.WAKE_LOCK;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView, imageView1, imageView2, imageView3, imageView4, imageView5;
    private TextView textView, textView1, textView2, textView3, textView4, textView5;
    private String appPackageName;
    private AppPreferences appPreferences = null;
    public static final int RequestPermissionCode = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appPackageName = getPackageName();

        appPreferences = new AppPreferences(this);
        if (!appPreferences.getBoolean(AppPreferences.PERMISSIONS)) {
            RequestMultiplePermission();
        }

        imageView = findViewById(R.id.imageView);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);

        textView = findViewById(R.id.textView);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);

        imageView.setOnClickListener(this);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        imageView5.setOnClickListener(this);


        textView.setOnClickListener(this);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView5.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView:
                Intent intent = new Intent(MainActivity.this, SearchSpecialist.class);
                startActivity(intent);
                break;
            case R.id.imageView1:
                Intent intent1 = new Intent(MainActivity.this, SearchDistrictAll.class);
                startActivity(intent1);
                break;
            case R.id.imageView2:
                Intent intent2 = new Intent(MainActivity.this, PhoneVerification.class);
                startActivity(intent2);
                break;
            case R.id.imageView3:
                Intent intent3 = new Intent(MainActivity.this, Complain.class);
                startActivity(intent3);
                break;
            case R.id.imageView4:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                break;
            case R.id.imageView5:
                Intent intent5 = new Intent(MainActivity.this, AboutUs.class);
                startActivity(intent5);
                break;
            case R.id.textView:
                Intent inte = new Intent(MainActivity.this, SearchSpecialist.class);
                startActivity(inte);
                break;
            case R.id.textView1:
                Intent inte1 = new Intent(MainActivity.this, SearchDistrictAll.class);
                startActivity(inte1);
                break;
            case R.id.textView2:
                Intent inte2 = new Intent(MainActivity.this, PhoneVerification.class);
                startActivity(inte2);
                break;
            case R.id.textView3:
                Intent inte3 = new Intent(MainActivity.this, Complain.class);
                startActivity(inte3);
                break;
            case R.id.textView4:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                break;
            case R.id.textView5:
                Intent inte5 = new Intent(MainActivity.this, AboutUs.class);
                startActivity(inte5);
                break;
        }
    }

    //Permission function starts from here
    private void RequestMultiplePermission() {

        // Creating String Array with Permissions.
        ActivityCompat.requestPermissions(MainActivity.this, new String[]
                {
                        //  ACCESS_FINE_LOCATION,
                        WRITE_EXTERNAL_STORAGE,
                        ACCESS_WIFI_STATE,
                        ACCESS_NETWORK_STATE,
                        WAKE_LOCK,
                        INTERNET
                        // READ_EXTERNAL_STORAGE
                }, RequestPermissionCode);

    }
}
