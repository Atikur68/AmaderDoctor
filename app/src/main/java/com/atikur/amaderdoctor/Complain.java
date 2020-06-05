package com.atikur.amaderdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Complain extends AppCompatActivity {
    Button comBtn;
    EditText compMobile;
    EditText compName;
    /* access modifiers changed from: private */
    public String compNameStr = "";
    /* access modifiers changed from: private */
    public String complainStr = "";
    EditText complains;
    /* access modifiers changed from: private */
    public String mobilenameStrng = "";
    /* access modifiers changed from: private */
    private String emailBody = "";
    private ProgressDialog progressDialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_complain);
        this.compName = (EditText) findViewById(R.id.compName);
        this.compMobile = (EditText) findViewById(R.id.compMobile);
        this.complains = (EditText) findViewById(R.id.complains);
        this.comBtn = (Button) findViewById(R.id.comBtn);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("অপেক্ষা করুন... ");


        this.comBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                progressDialog.show();
                boolean checker = true;
                compNameStr = compName.getText().toString();
                mobilenameStrng = compMobile.getText().toString();
                complainStr = complains.getText().toString();
                emailBody = compNameStr + " --- " + mobilenameStrng + " --- " + complainStr;

                if (compNameStr.isEmpty()) {
                    compName.setError("ফাকা রাখা যাবে না ");
                    checker = false;
                } else {
                    compName.setError(null);
                }
                if (mobilenameStrng.isEmpty()) {
                    compMobile.setError("ফাকা রাখা যাবে না ");
                    checker = false;
                } else {
                    compMobile.setError(null);
                }
                if (complainStr.isEmpty()) {
                    complains.setError("ফাকা রাখা যাবে না ");
                    checker = false;
                } else {
                    complains.setError(null);
                }
                if (checker) {

                    new LongOperation().execute();

                    RequestQueue newRequestQueue = Volley.newRequestQueue(Complain.this);

                    StringRequest r0 = new StringRequest(Request.Method.POST, "https://amaderdoctor.timitbd.com/ComplainAdd.php", new Response.Listener<String>() {
                        public void onResponse(String ServerResponse) {

                            if (ServerResponse.contains("userall")) {
//                    try {
//                        JSONArray heroArray = new JSONObject(ServerResponse).getJSONArray("userall");
//                        for (int i = 0; i < heroArray.length(); i++) {
//                            JSONObject heroObject = heroArray.getJSONObject(i);
//                            Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
//
//
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }

                                Toast.makeText(Complain.this, "তথ্য পাঠানো সম্পন্ন হয়েছে। নিশ্চিতকরণের জন্য অপেক্ষা করুন।", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(Complain.this, "ইন্টারনেট সংযোগ ঠিক নেই ", Toast.LENGTH_LONG).show();

                            }
                        }
                    }, new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError volleyError) {

                            Toast.makeText(Complain.this, "ইন্টারনেট সংযোগ ঠিক নেই ", Toast.LENGTH_LONG).show();

                        }
                    }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json");
//                return headers;
//            }

                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("compNameStr", compNameStr);
                            params.put("mobilenameStrng", mobilenameStrng);
                            params.put("complainStr", complainStr);

                            return params;
                        }
                    };
                    Volley.newRequestQueue(Complain.this).add(r0);

                    compName.setText("");
                    compMobile.setText("");
                    complains.setText("");
                    progressDialog.dismiss();
                    startActivity(new Intent(Complain.this, MainActivity.class));
                    finish();

                    //  dataInsert();

                }
            }
        });


    }

    public class LongOperation extends AsyncTask<Void, Void, String> {



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            try {


//            GMailSender sender = new GMailSender("sender.sendermail.com", "senders password");
//            sender.sendMail("subject",
//                    "body",
//                    "sender.sendermail.com",
//                    "reciepients.recepientmail.com");

                //   "pankajgurjar90@gmail.com,gsolc.developers@gmail.com


//
                GMailSender sender = new GMailSender("atikalif019@gmail.com", "atikalif019@");
                sender.sendMail("Amader Doctor/// Complain from Apps",
                        emailBody, "atikalif019@gmail.com",
                        "atikalif007@gmail.com");



            } catch (Exception e) {
                Log.e("error", e.getMessage(), e);
                return "Data Not Sent! Try again.";

            }

            return "Data Sent. Please wait for confirmation.";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.e("LongOperation", result + "");
        }


        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

}

