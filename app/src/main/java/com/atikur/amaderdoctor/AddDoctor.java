package com.atikur.amaderdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddDoctor extends AppCompatActivity {
    /* access modifiers changed from: private */
    private String chamberString = "", designationString = "", distDivStr = "", docDegreeStrng = "", docnameStrng = "";
    /* access modifiers changed from: private */
    private EditText distDiv, docotorEmail, docotorMobile, doctorChamber, doctorChambertime, doctorDegree, doctorDesignation, doctorName, specialistOn;
    private String doctorChambertimestr = "", emailString = "", mobilenameStrng = "", specialityon = "";
    private ProgressDialog progressDialog;
    private Button subbtn;
    private String emailBody = "";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_add_doctor);
        this.doctorName = (EditText) findViewById(R.id.doctorName);
        this.doctorDegree = (EditText) findViewById(R.id.doctorDegree);
        this.doctorDesignation = (EditText) findViewById(R.id.doctorDesignation);
        this.specialistOn = (EditText) findViewById(R.id.specialistOn);
        this.docotorMobile = (EditText) findViewById(R.id.doctorMobile);
        this.docotorEmail = (EditText) findViewById(R.id.doctorEmail);
        this.doctorChamber = (EditText) findViewById(R.id.doctorChamber);
        this.distDiv = (EditText) findViewById(R.id.distDiv);
        this.doctorChambertime = (EditText) findViewById(R.id.doctorChambertime);
        this.subbtn = (Button) findViewById(R.id.signUpBtn);
        this.subbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dataInsert();
            }
        });
    }


    /* access modifiers changed from: private */
    public void dataInsert() {
        boolean checker = true;
        this.docnameStrng = this.doctorName.getText().toString();
        this.docDegreeStrng = this.doctorDegree.getText().toString();
        this.emailString = this.docotorEmail.getText().toString();
        this.mobilenameStrng = this.docotorMobile.getText().toString();
        this.designationString = this.doctorDesignation.getText().toString();
        this.chamberString = this.doctorChamber.getText().toString();
        this.distDivStr = this.distDiv.getText().toString();
        this.specialityon = this.specialistOn.getText().toString();
        this.doctorChambertimestr = this.doctorChambertime.getText().toString();
        if (this.docnameStrng.isEmpty()) {
            this.doctorName.setError("ফাকা রাখা যাবে না ");
            checker = false;
        } else {
            this.doctorName.setError(null);
        }
        if (this.docDegreeStrng.isEmpty()) {
            this.doctorDegree.setError("ফাকা রাখা যাবে না ");
            checker = false;
        } else {
            this.doctorDegree.setError(null);
        }
        if (this.mobilenameStrng.isEmpty() || this.mobilenameStrng.length() != 11) {
            this.docotorMobile.setError("সঠিক নম্বর দিন");
            checker = false;
        } else {
            this.docotorMobile.setError(null);
        }
        if (this.designationString.isEmpty()) {
            this.doctorDesignation.setError("ফাকা রাখা যাবে না ");
            checker = false;
        } else {
            this.doctorDesignation.setError(null);
        }
//        if (this.chamberString.isEmpty()) {
//            this.doctorChamber.setError("ফাকা রাখা যাবে না ");
//            checker = false;
//        } else {
//            this.doctorChamber.setError(null);
//        }
        if (this.distDivStr.isEmpty()) {
            this.distDiv.setError("ফাকা রাখা যাবে না ");
            checker = false;
        } else {
            this.distDiv.setError(null);
        }
        if (this.emailString.isEmpty()) {
            this.docotorEmail.setError("ফাকা রাখা যাবে না ");
            checker = false;
        } else {
            this.docotorEmail.setError(null);
        }
        if (this.specialityon.isEmpty()) {
            this.specialistOn.setError("ফাকা রাখা যাবে না ");
            checker = false;
        } else {
            this.specialistOn.setError(null);
        }
//        if (this.doctorChambertimestr.isEmpty()) {
//            this.doctorChambertime.setError("ফাকা রাখা যাবে না ");
//            checker = false;
//        } else {
//            this.doctorChambertime.setError(null);
//        }
        if (checker) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("অপেক্ষা করুন... ");
            progressDialog.show();

            emailBody = docnameStrng+" --- " + docDegreeStrng+" --- " + designationString+" --- " + specialityon+" --- " + mobilenameStrng+" --- " + emailString+" --- " + chamberString+" --- " + doctorChambertimestr+" --- " + distDivStr;

            try {
                LongOperation l = new LongOperation();
                l.execute();  //sends the email in background
                Toast.makeText(this, l.get(), Toast.LENGTH_LONG).show();

                AddDoctor.this.doctorName.setText("");
                AddDoctor.this.doctorDegree.setText("");
                AddDoctor.this.doctorDesignation.setText("");
                AddDoctor.this.doctorChamber.setText("");
                AddDoctor.this.docotorMobile.setText("");
                AddDoctor.this.docotorEmail.setText("");
                AddDoctor.this.distDiv.setText("");
                AddDoctor.this.specialistOn.setText("");
                progressDialog.dismiss();
                startActivity(new Intent(AddDoctor.this,MainActivity.class));


            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }


//            RequestQueue newRequestQueue = Volley.newRequestQueue(this);
//            this.progressDialog = new ProgressDialog(this);
//            this.progressDialog.setMessage("অপেক্ষা করুন...");
//            this.progressDialog.show();
//            StringRequest r1 = new StringRequest(1, "http://timitbd.com/taka/AppInsert.php", new Response.Listener<String>() {
//                public void onResponse(String ServerResponse) {
//                    AddDoctor.this.progressDialog.dismiss();
//                    if (ServerResponse.contains("userall")) {
//                        AddDoctor.this.doctorName.setText("");
//                        AddDoctor.this.doctorDegree.setText("");
//                        AddDoctor.this.doctorDesignation.setText("");
//                        AddDoctor.this.doctorChamber.setText("");
//                        AddDoctor.this.docotorMobile.setText("");
//                        AddDoctor.this.docotorEmail.setText("");
//                        AddDoctor.this.distDiv.setText("");
//                        AddDoctor.this.specialistOn.setText("");
//                        Toast.makeText(AddDoctor.this, "সংযোজন সফল হয়েছে।", Toast.LENGTH_LONG).show();
//                        return;
//                    }
//                    Toast.makeText(AddDoctor.this, "কিছু ভুল হয়েছে। আবার চেষ্টা করুন।", Toast.LENGTH_LONG).show();
//                }
//            }, new Response.ErrorListener() {
//                public void onErrorResponse(VolleyError volleyError) {
//                    AddDoctor.this.progressDialog.dismiss();
//                    Toast.makeText(AddDoctor.this, volleyError.toString(), Toast.LENGTH_LONG).show();
//                }
//            }) {
//                /* access modifiers changed from: protected */
//                public Map<String, String> getParams() {
//                    Map<String, String> params = new HashMap<>();
//                    params.put("docnameStrng", AddDoctor.this.docnameStrng);
//                    params.put("mobilenameStrng", AddDoctor.this.mobilenameStrng.trim());
//                    params.put("docDegreeStrng", AddDoctor.this.docDegreeStrng);
//                    params.put("emailString", AddDoctor.this.emailString.trim());
//                    params.put("designationString", AddDoctor.this.designationString);
//                    params.put("distDivStr", AddDoctor.this.distDivStr);
//                    params.put("chamberString", AddDoctor.this.chamberString);
//                    params.put("specialityon", AddDoctor.this.specialityon);
//                    params.put("doctorChambertimestr", AddDoctor.this.doctorChambertimestr);
//                    return params;
//                }
//            };
//            Volley.newRequestQueue(this).add(r1);
        }
    }

    public class LongOperation extends AsyncTask<Void, Void, String> {
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
                sender.sendMail("Amader Doctor/// Mail from Apps",
                        emailBody, "atikalif019@gmail.com",
                        "atikalif007@gmail.com,atikalif019@gmail.com    ");

            } catch (Exception e) {
                Log.e("error", e.getMessage(), e);
                return "Data Not Sent! Try again.";
            }
            return "Data Sent. Please wait for confirmation.";
        }

        @Override
        protected void onPostExecute(String result) {

            Log.e("LongOperation", result + "");
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

}
