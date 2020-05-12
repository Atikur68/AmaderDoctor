package com.atikur.amaderdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

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
    Bundle bnd;
    TextView docChamber;
    TextView docDegree;
    TextView docDesignation;
    TextView docEmail;
    TextView docMobile;
    TextView docName;
    TextView docSpeciality;
    /* access modifiers changed from: private */
    public ProgressDialog progressDialog;
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
        this.bnd = getIntent().getExtras();
        this.str = this.bnd.getString("appId");
        specialistDoctor();
    }

    private void specialistDoctor() {
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setMessage("অপেক্ষা করুন... ");
        this.progressDialog.show();
        StringRequest r0 = new StringRequest(1, "http://timitbd.com/taka/DoctorDetailsApp.php", new Response.Listener<String>() {
            public void onResponse(String ServerResponse) {
                Doctor_Details.this.progressDialog.dismiss();
                if (ServerResponse.contains("userall")) {
                    try {
                        JSONArray heroArray = new JSONObject(ServerResponse).getJSONArray("userall");
                        for (int i = 0; i < heroArray.length(); i++) {
                            JSONObject heroObject = heroArray.getJSONObject(i);
                            Doctor_Details.this.docName.setText(heroObject.getString("doctorName"));
                            Doctor_Details.this.docDegree.setText(heroObject.getString("degree"));
                            Doctor_Details.this.docDesignation.setText(heroObject.getString("designation"));
                            Doctor_Details.this.docSpeciality.setText(heroObject.getString("specialist_on"));
                            Doctor_Details.this.docMobile.setText(heroObject.getString("mobile"));
                            Doctor_Details.this.docEmail.setText(heroObject.getString("email"));
                            Doctor_Details.this.docChamber.setText(heroObject.getString("chamber"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(Doctor_Details.this, "সঠিক তথ্য দিন ", Toast.LENGTH_LONG).show();
                    Doctor_Details.this.docName.setText("");
                    Doctor_Details.this.docDegree.setText("");
                    Doctor_Details.this.docDesignation.setText("");
                    Doctor_Details.this.docSpeciality.setText("");
                    Doctor_Details.this.docMobile.setText("");
                    Doctor_Details.this.docEmail.setText("");
                    Doctor_Details.this.docChamber.setText("");
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Doctor_Details.this.progressDialog.dismiss();
                Toast.makeText(Doctor_Details.this, "ইন্টারনেট সংযোগ ঠিক নেই ", Toast.LENGTH_LONG).show();
                Doctor_Details.this.docName.setText("");
                Doctor_Details.this.docDegree.setText("");
                Doctor_Details.this.docDesignation.setText("");
                Doctor_Details.this.docSpeciality.setText("");
                Doctor_Details.this.docMobile.setText("");
                Doctor_Details.this.docEmail.setText("");
                Doctor_Details.this.docChamber.setText("");
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("appid", Doctor_Details.this.str);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(r0);
    }
}

