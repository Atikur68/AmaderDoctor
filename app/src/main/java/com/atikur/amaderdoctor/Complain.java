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
    public ProgressDialog progressDialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_complain);
        this.compName = (EditText) findViewById(R.id.compName);
        this.compMobile = (EditText) findViewById(R.id.compMobile);
        this.complains = (EditText) findViewById(R.id.complains);
        this.comBtn = (Button) findViewById(R.id.comBtn);
        this.comBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Complain.this.dataInsert();
            }
        });
    }

    /* access modifiers changed from: private */
    public void dataInsert() {
        boolean checker = true;
        this.compNameStr = this.compName.getText().toString();
        this.mobilenameStrng = this.compMobile.getText().toString();
        this.complainStr = this.complains.getText().toString();
        if (this.compNameStr.isEmpty()) {
            this.compName.setError("ফাকা রাখা যাবে না ");
            checker = false;
        } else {
            this.compName.setError(null);
        }
        if (this.mobilenameStrng.isEmpty()) {
            this.compMobile.setError("ফাকা রাখা যাবে না ");
            checker = false;
        } else {
            this.compMobile.setError(null);
        }
        if (this.complainStr.isEmpty()) {
            this.complains.setError("ফাকা রাখা যাবে না ");
            checker = false;
        } else {
            this.complains.setError(null);
        }
        if (checker) {
            RequestQueue newRequestQueue = Volley.newRequestQueue(this);
            this.progressDialog = new ProgressDialog(this);
            this.progressDialog.setMessage("অপেক্ষা করুন...");
            this.progressDialog.show();
            StringRequest r1 = new StringRequest(1, "http://timitbd.com/taka/ComplainAdd.php", new Response.Listener<String>() {
                public void onResponse(String ServerResponse) {
                    Complain.this.progressDialog.dismiss();
                    if (ServerResponse.contains("userall")) {
                        Complain.this.compName.setText("");
                        Complain.this.compMobile.setText("");
                        Complain.this.complains.setText("");
                        Toast.makeText(Complain.this, "সংযোজন সফল হয়েছে।", Toast.LENGTH_LONG).show();
                        return;
                    }
                    Toast.makeText(Complain.this, "কিছু ভুল হয়েছে। আবার চেষ্টা করুন।", Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    Complain.this.progressDialog.dismiss();
                    Toast.makeText(Complain.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("compNameStr", Complain.this.compNameStr);
                    params.put("mobilenameStrng", Complain.this.mobilenameStrng.trim());
                    params.put("complainStr", Complain.this.complainStr);
                    return params;
                }
            };
            Volley.newRequestQueue(this).add(r1);
        }
    }
}

