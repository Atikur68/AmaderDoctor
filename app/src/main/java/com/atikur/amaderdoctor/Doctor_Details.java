package com.atikur.amaderdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Doctor_Details extends AppCompatActivity {
    private Bundle bnd;
    private TextView docChamber, docDegree, docDesignation, docEmail, docMobile, docName, docSpeciality,district,division;
    private ProgressDialog progressDialog;
    String str;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_doctor__details);
        docName = (TextView) findViewById(R.id.docName);
        docDegree = (TextView) findViewById(R.id.docDegree);
        docDesignation = (TextView) findViewById(R.id.docDesignation);
        docSpeciality = (TextView) findViewById(R.id.docSpeciality);
        docMobile = (TextView) findViewById(R.id.docMobile);
        docEmail = (TextView) findViewById(R.id.docEmail);
        docChamber = (TextView) findViewById(R.id.docChamber);
        division = (TextView) findViewById(R.id.docDivision);
        district = (TextView) findViewById(R.id.docDistrict);
        bnd = getIntent().getExtras();
        str = bnd.getString("appId");

        String mobilenumber= bnd.getString("mobile");
        String emailAddres= bnd.getString("email");

//        if(mobilenumber.contains("N/A")){
//
//            docMobile.setVisibility(View.GONE);
//
//        }else {
//            docMobile.setText(bnd.getString("mobile"));
//        }
//
//
//        if(emailAddres.contains("N/A")){
//
//            docEmail.setVisibility(View.GONE);
//
//        }else {
//            docEmail.setText(bnd.getString("email"));
//        }

        docName.setText(bnd.getString("docName"));
        docDegree.setText(bnd.getString("degree"));
        docDesignation.setText(bnd.getString("designation"));
        docSpeciality.setText(bnd.getString("specialist_on"));
        docMobile.setText(bnd.getString("mobile"));
        docEmail.setText(bnd.getString("email"));
        docChamber.setText(bnd.getString("chamber"));
        division.setText(bnd.getString("division"));
        district.setText(bnd.getString("district"));


       // specialistDoctor();
    }


}

