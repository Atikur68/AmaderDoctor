package com.atikur.amaderdoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneVerification extends AppCompatActivity {
    FirebaseAuth auth;
    Button btnGenerateOTP;
    Button btnSignIn;
    EditText etOTP;
    EditText etPhoneNumber;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    String otp;
    String phoneNumber;
    /* access modifiers changed from: private */
    public String verificationCode;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification);
        findViews();
        StartFirebaseLogin();
        this.btnGenerateOTP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PhoneVerification phoneVerification = PhoneVerification.this;
                StringBuilder sb = new StringBuilder();
                sb.append("+88");
                sb.append(PhoneVerification.this.etPhoneNumber.getText().toString());
                phoneVerification.phoneNumber = sb.toString();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(PhoneVerification.this.phoneNumber, 60, TimeUnit.SECONDS, (Activity) PhoneVerification.this, PhoneVerification.this.mCallback);
            }
        });
        this.btnSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PhoneVerification.this.otp = PhoneVerification.this.etOTP.getText().toString();
                PhoneVerification.this.SigninWithPhone(PhoneAuthProvider.getCredential(PhoneVerification.this.verificationCode, PhoneVerification.this.otp));
            }
        });
    }

    /* access modifiers changed from: private */
    public void SigninWithPhone(PhoneAuthCredential credential) {
        this.auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    PhoneVerification.this.startActivity(new Intent(PhoneVerification.this, AddDoctor.class));
                    PhoneVerification.this.finish();
                    return;
                }
                Toast.makeText(PhoneVerification.this, "Incorrect Code", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void findViews() {
        this.btnGenerateOTP = (Button) findViewById(R.id.btn_generate_otp);
        this.btnSignIn = (Button) findViewById(R.id.btn_sign_in);
        this.etPhoneNumber = (EditText) findViewById(R.id.et_phone_number);
        this.etOTP = (EditText) findViewById(R.id.et_otp);
    }

    private void StartFirebaseLogin() {
        this.auth = FirebaseAuth.getInstance();
        this.mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(PhoneVerification.this, "verification completed", Toast.LENGTH_LONG).show();
            }

            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(PhoneVerification.this, "verification fialed", Toast.LENGTH_LONG).show();
            }

            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                PhoneVerification.this.verificationCode = s;
                Toast.makeText(PhoneVerification.this, "Code sent", Toast.LENGTH_LONG).show();
            }
        };
    }
}
