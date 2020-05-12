package com.atikur.amaderdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
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
    public String chamberString = "";
    /* access modifiers changed from: private */
    public String designationString = "";
    EditText distDiv;
    /* access modifiers changed from: private */
    public String distDivStr = "";
    /* access modifiers changed from: private */
    public String docDegreeStrng = "";
    /* access modifiers changed from: private */
    public String docnameStrng = "";
    EditText docotorEmail;
    EditText docotorMobile;
    EditText doctorChamber;
    EditText doctorChambertime;
    /* access modifiers changed from: private */
    public String doctorChambertimestr = "";
    EditText doctorDegree;
    EditText doctorDesignation;
    EditText doctorName;
    /* access modifiers changed from: private */
    public String emailString = "";
    /* access modifiers changed from: private */
    public String mobilenameStrng = "";
    /* access modifiers changed from: private */
    public ProgressDialog progressDialog;
    EditText specialistOn;
    /* access modifiers changed from: private */
    public String specialityon = "";
    Button subbtn;

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
                AddDoctor.this.dataInsert();
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
            RequestQueue newRequestQueue = Volley.newRequestQueue(this);
            this.progressDialog = new ProgressDialog(this);
            this.progressDialog.setMessage("অপেক্ষা করুন...");
            this.progressDialog.show();
            StringRequest r1 = new StringRequest(1, "http://timitbd.com/taka/AppInsert.php", new Response.Listener<String>() {
                public void onResponse(String ServerResponse) {
                    AddDoctor.this.progressDialog.dismiss();
                    if (ServerResponse.contains("userall")) {
                        AddDoctor.this.doctorName.setText("");
                        AddDoctor.this.doctorDegree.setText("");
                        AddDoctor.this.doctorDesignation.setText("");
                        AddDoctor.this.doctorChamber.setText("");
                        AddDoctor.this.docotorMobile.setText("");
                        AddDoctor.this.docotorEmail.setText("");
                        AddDoctor.this.distDiv.setText("");
                        AddDoctor.this.specialistOn.setText("");
                        Toast.makeText(AddDoctor.this, "সংযোজন সফল হয়েছে।", Toast.LENGTH_LONG).show();
                        return;
                    }
                    Toast.makeText(AddDoctor.this, "কিছু ভুল হয়েছে। আবার চেষ্টা করুন।", Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    AddDoctor.this.progressDialog.dismiss();
                    Toast.makeText(AddDoctor.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("docnameStrng", AddDoctor.this.docnameStrng);
                    params.put("mobilenameStrng", AddDoctor.this.mobilenameStrng.trim());
                    params.put("docDegreeStrng", AddDoctor.this.docDegreeStrng);
                    params.put("emailString", AddDoctor.this.emailString.trim());
                    params.put("designationString", AddDoctor.this.designationString);
                    params.put("distDivStr", AddDoctor.this.distDivStr);
                    params.put("chamberString", AddDoctor.this.chamberString);
                    params.put("specialityon", AddDoctor.this.specialityon);
                    params.put("doctorChambertimestr", AddDoctor.this.doctorChambertimestr);
                    return params;
                }
            };
            Volley.newRequestQueue(this).add(r1);
        }
    }
}
