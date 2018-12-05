package com.example.sylviaputri.l_pay;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class RegistrationActivity extends AppCompatActivity {
    private static long back_pressed;
    public TextView edtRegisName;
    public TextView edtRegisPhoneNumber;
    public TextView edtRegisEmail;
    public TextView edtRegisPassword;
    public TextView edtRegisConfirmPassword;
    private ProgressDialog progressDialog;
    public Button btnRegisRegister;
    public Button btnRegisLogin;
    private FirebaseAuth mAuth;
    private ValueEventListener valueEvent;
    private HashMap<String, Boolean> hm;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbuser = database.getReference("pembeli");
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("ListPhone");

    @Override
    public void onStart() {
        super.onStart();
        valueEvent = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hm = (HashMap) dataSnapshot.getValue();
                if(progressDialog.isShowing())
                    progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabase.addValueEventListener(valueEvent);
    }

    @Override
    protected void onStop() {
        mDatabase.removeEventListener(valueEvent);
        super.onStop();
    }

    private Boolean isUserExist(String number){
        Boolean cek = false;
        try {
            if(hm.get(number)){
                return true;
            }
            else{
                return false;
            }
        }catch (Exception e){
            return false;
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        btnRegisLogin = (Button) findViewById(R.id.btnRegisLogin);
        btnRegisRegister = (Button) findViewById(R.id.btnRegisRegister);
        edtRegisName=(TextView) findViewById(R.id.edtRegisName);
        edtRegisPhoneNumber=(TextView) findViewById(R.id.edtRegisPhoneNumber);
        edtRegisEmail=(TextView) findViewById(R.id.edtRegisEmail);
        edtRegisPassword=(TextView) findViewById(R.id.edtRegisPassword);
        edtRegisConfirmPassword = findViewById(R.id.edtRegisConfirmPassword);
        progressDialog = new ProgressDialog(this);

        btnRegisLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegisRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(RegistrationActivity.this, HomeActivity.class);
                //startActivity(intent);

                String userName = edtRegisName.getText().toString();
                String userPhone = edtRegisPhoneNumber.getText().toString();
                String userEmail = edtRegisEmail.getText().toString();
                String userPassword = edtRegisPassword.getText().toString();
                String userConfirmPassword = edtRegisConfirmPassword.getText().toString();

                if(userEmail.equals("") || userPhone.equals("") || userEmail.equals("") || userPassword.equals("") || userConfirmPassword.equals("")){
                    Toast.makeText(RegistrationActivity.this,"Data incomplete",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    if(!userPassword.equals(userConfirmPassword)){
                        edtRegisConfirmPassword.requestFocus();
                        Toast.makeText(RegistrationActivity.this,"beda",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(isValidEmail(userEmail)){
                            if(isUserExist(userPhone)){
                                Toast.makeText(RegistrationActivity.this,"phone number already exist",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                regis();
                            }
                        }
                        else{
                            Toast.makeText(RegistrationActivity.this,"not valid",Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });
    }

    public void regis(){

        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                edtRegisPhoneNumber.getText().toString(),        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.

            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                // ...
                Toast.makeText(RegistrationActivity.this,"Failed",Toast.LENGTH_LONG).show();
            } else if (e instanceof FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                // ...
                progressDialog.dismiss();
                Toast.makeText(RegistrationActivity.this,"Too Many Attempt",Toast.LENGTH_LONG).show();
            }

            // Show a message and update the UI
            // ...
        }

        @Override
        public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            //Log.d(TAG, "onCodeSent:" + verificationId);
            super.onCodeSent(verificationId,token);

            progressDialog.dismiss();
            Intent intent = new Intent(RegistrationActivity.this,VerificationNumberActivity.class);
            intent.putExtra("Code",verificationId);

            String userName = edtRegisName.getText().toString();
            String userEmail = edtRegisEmail.getText().toString();
            String userPassword = edtRegisPassword.getText().toString();

            intent.putExtra("storeName",userName);
            intent.putExtra("storeEmail",userEmail);
            intent.putExtra("storePassword",userPassword);
            intent.putExtra("context","Regis");

            startActivity(intent);
            //Toast.makeText(RegistrationActivity.this,"Sukses Lur!",Toast.LENGTH_LONG).show();

            // ...

            //Toast.makeText(RegistrationActivity.this,"Sukses Lur!",Toast.LENGTH_LONG).show();



        }
    };

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.addFlags((Intent.FLAG_ACTIVITY_CLEAR_TOP));
            startActivity(intent);
            finish();
        } else{
            Toast.makeText(this, "Press once again to exit",Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
}
