package com.atikur.amaderdoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
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
    private FirebaseAuth auth;
    private Button btnGenerateOTP,btnSignIn;
    private TextView btnResend;
    private EditText etOTP,etPhoneNumber;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    private String otp,phoneNumber;
    /* access modifiers changed from: private */
    public String verificationCode;
    private LinearLayout llverificationCode, llphoneNumber;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification);
        findViews();
        StartFirebaseLogin();
        btnGenerateOTP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PhoneVerification phoneVerification = PhoneVerification.this;
                StringBuilder sb = new StringBuilder();
                sb.append("+88");
                sb.append(PhoneVerification.this.etPhoneNumber.getText().toString());
                phoneVerification.phoneNumber = sb.toString();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(PhoneVerification.this.phoneNumber, 60, TimeUnit.SECONDS, (Activity) PhoneVerification.this, PhoneVerification.this.mCallback);

                llphoneNumber.setVisibility(View.GONE);
                llverificationCode.setVisibility(View.VISIBLE);
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PhoneVerification.this.otp = PhoneVerification.this.etOTP.getText().toString();
                PhoneVerification.this.SigninWithPhone(PhoneAuthProvider.getCredential(PhoneVerification.this.verificationCode, PhoneVerification.this.otp));
            }
        });

        btnResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llphoneNumber.setVisibility(View.VISIBLE);
                llverificationCode.setVisibility(View.GONE);
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
        btnGenerateOTP = findViewById(R.id.btn_generate_otp);
        btnSignIn = findViewById(R.id.btn_sign_in);
        etPhoneNumber = findViewById(R.id.et_phone_number);
        etOTP = findViewById(R.id.et_otp);
        llverificationCode = findViewById(R.id.verificationCode);
        llphoneNumber = findViewById(R.id.phoneNumber);
        btnResend = findViewById(R.id.btnResend);

    }

    private void StartFirebaseLogin() {
        this.auth = FirebaseAuth.getInstance();
        this.mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
              //  Toast.makeText(PhoneVerification.this, "verification completed", Toast.LENGTH_LONG).show();
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
