package com.atikur.amaderdoctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
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

public class AboutUs extends AppCompatActivity {

    private InfoCollectorAdapter infoCollectorAdapter;
    private List<Doctor_List> docLists = new ArrayList();
    /* access modifiers changed from: private */
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        recyclerView = (RecyclerView) findViewById(R.id.recyco_about);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        infoCollectorFind();
    }

    private void infoCollectorFind() {

        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("অপেক্ষা করুন... ");
        progressDialog.show();
        StringRequest r0 = new StringRequest(Request.Method.POST, "https://amaderdoctor.timitbd.com/infoCollector.php", new Response.Listener<String>() {
            public void onResponse(String ServerResponse) {
                progressDialog.dismiss();
                if (ServerResponse.contains("userall")) {
                    try {
                        JSONArray heroArray = new JSONObject(ServerResponse).getJSONArray("userall");
                        for (int i = 0; i < heroArray.length(); i++) {
                            JSONObject heroObject = heroArray.getJSONObject(i);
                            // List<Doctor_List> list = docLists;
                            docLists.add(new Doctor_List(heroObject.getString("name").trim()));

                        }

                        infoCollectorAdapter = new InfoCollectorAdapter(AboutUs.this, docLists);
                        recyclerView.setAdapter(infoCollectorAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {

                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                progressDialog.dismiss();

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
                return params;
            }

        };
        Volley.newRequestQueue(this).add(r0);
    }
}
