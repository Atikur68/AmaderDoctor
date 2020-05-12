package com.atikur.amaderdoctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorsSearchResult extends AppCompatActivity {
    DoctorAdapter adapter;
    Bundle bd;
    String district;
    List<Doctor_List> docLists = new ArrayList();
    /* access modifiers changed from: private */
    public ProgressDialog progressDialog;
    RecyclerView recyclerView;
    String specialist;
    TextView txt7;
    TextView txt8;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_doctors_search_result);
        this.bd = getIntent().getExtras();
        this.district = this.bd.getString("District");
        this.specialist = this.bd.getString("Specialist");
        specialistDoctor();
        this.recyclerView = (RecyclerView) findViewById(R.id.recy);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void specialistDoctor() {
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setMessage("অপেক্ষা করুন... ");
        this.progressDialog.show();
        StringRequest r0 = new StringRequest(1, "http://timitbd.com/taka/specialistdoctorSearch.php", new Response.Listener<String>() {
            public void onResponse(String ServerResponse) {
                DoctorsSearchResult.this.progressDialog.dismiss();
                if (ServerResponse.contains("userall")) {
                    try {
                        JSONArray heroArray = new JSONObject(ServerResponse).getJSONArray("userall");
                        for (int i = 0; i < heroArray.length(); i++) {
                            JSONObject heroObject = heroArray.getJSONObject(i);
                            List<Doctor_List> list = DoctorsSearchResult.this.docLists;
                            Doctor_List doctor_List = new Doctor_List(1, heroObject.getString("doctorName"), heroObject.getString("specialist_on"), heroObject.getString("district"), heroObject.getString("id"));
                            list.add(doctor_List);
                        }
                        DoctorsSearchResult.this.adapter = new DoctorAdapter(DoctorsSearchResult.this, DoctorsSearchResult.this.docLists);
                        DoctorsSearchResult.this.recyclerView.setAdapter(DoctorsSearchResult.this.adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(DoctorsSearchResult.this, "সঠিক তথ্য দিন ", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                DoctorsSearchResult.this.progressDialog.dismiss();
                Toast.makeText(DoctorsSearchResult.this, volleyError.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("district", DoctorsSearchResult.this.district);
                params.put("specialist", DoctorsSearchResult.this.specialist);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(r0);
    }
}
