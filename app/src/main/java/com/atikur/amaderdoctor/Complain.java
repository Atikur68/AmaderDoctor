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

            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("অপেক্ষা করুন... ");
            progressDialog.show();
            emailBody = compNameStr+" --- " + mobilenameStrng+" --- " + complainStr;


            try {
               LongOperation l = new LongOperation();
                l.execute();  //sends the email in background
                Toast.makeText(this, l.get(), Toast.LENGTH_LONG).show();


                compName.setText("");
                compMobile.setText("");
                complains.setText("");
                progressDialog.dismiss();
                startActivity(new Intent(Complain.this,MainActivity.class));


            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }

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
                sender.sendMail("Amader Doctor/// Complain from Apps",
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

