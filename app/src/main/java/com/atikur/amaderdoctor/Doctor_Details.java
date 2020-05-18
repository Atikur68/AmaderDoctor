package com.atikur.amaderdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
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
        this.docName = (TextView) findViewById(R.id.docName);
        this.docDegree = (TextView) findViewById(R.id.docDegree);
        this.docDesignation = (TextView) findViewById(R.id.docDesignation);
        this.docSpeciality = (TextView) findViewById(R.id.docSpeciality);
        this.docMobile = (TextView) findViewById(R.id.docMobile);
        this.docEmail = (TextView) findViewById(R.id.docEmail);
        this.docChamber = (TextView) findViewById(R.id.docChamber);
        this.division = (TextView) findViewById(R.id.docDivision);
        this.district = (TextView) findViewById(R.id.docDistrict);
        this.bnd = getIntent().getExtras();
        this.str = this.bnd.getString("appId");

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

