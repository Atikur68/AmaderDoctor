package com.atikur.amaderdoctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorSearchAll extends AppCompatActivity {
    private DoctorAdapter adapter;
    private Bundle bd;
    private String district;
    private List<Doctor_List> docLists = new ArrayList();
    /* access modifiers changed from: private */
    private ProgressDialog progressDialog;
    private  RecyclerView recyclerView;
    private TextView txt7,txtwarning;
    private TextView txt8;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_doctor_search_all);
        bd = getIntent().getExtras();
        district = this.bd.getString("District");

        txtwarning=findViewById(R.id.txtwarning);
       // txtwarning.setVisibility(View.GONE);

        recyclerView = (RecyclerView) findViewById(R.id.recyco);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        specialistDoctor();
    }

    private void specialistDoctor() {
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("অপেক্ষা করুন... ");
        progressDialog.show();
        StringRequest r0 = new StringRequest(Request.Method.POST, "https://amaderdoctor.timitbd.com/districtdoctorSearch.php", new Response.Listener<String>() {
            public void onResponse(String ServerResponse) {
                progressDialog.dismiss();
                if (ServerResponse.contains("userall")) {
                    try {
                        JSONArray heroArray = new JSONObject(ServerResponse).getJSONArray("userall");
                        int count=0;
                        for (int i = 0; i < heroArray.length(); i++) {
                            JSONObject heroObject = heroArray.getJSONObject(i);
                            // List<Doctor_List> list = docLists;
                            String dist=heroObject.getString("district");

                            if(dist.equals(district)){
                                count=1;
                                docLists.add(new Doctor_List(1, heroObject.getString("doctorName"),heroObject.getString("degree"),heroObject.getString("designation"), heroObject.getString("specialist_on"),heroObject.getString("mobile"),heroObject.getString("email"),heroObject.getString("chamber"),heroObject.getString("division"), heroObject.getString("district"), heroObject.getString("id")));
                            }

                        }
                        if(count==0){
                            recyclerView.setVisibility(View.GONE);
                            txtwarning.setVisibility(View.VISIBLE);
                        }
                        adapter = new DoctorAdapter(DoctorSearchAll.this, docLists);
                        recyclerView.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    recyclerView.setVisibility(View.GONE);
                    txtwarning.setVisibility(View.VISIBLE);
                    Toast.makeText(DoctorSearchAll.this, "সঠিক তথ্য দিন ", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                progressDialog.dismiss();
                recyclerView.setVisibility(View.GONE);
                txtwarning.setVisibility(View.VISIBLE);
                Toast.makeText(DoctorSearchAll.this, volleyError.toString(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("district", district);
                return params;
            }

        };
        Volley.newRequestQueue(this).add(r0);
    }
}
