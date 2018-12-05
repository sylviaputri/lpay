package com.example.sylviaputri.l_pay;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sylviaputri.l_pay.Model.AuthUser;
import com.example.sylviaputri.l_pay.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    public ImageButton btnHomeTopUp;
    public ImageButton btnHomeWithdraw;
    public ImageButton btnHomeScanQR;
    public ImageView imgNavHomeSetting;
    public ImageView imgNavHomeHistory;
    public TextView txtHomeHome;
    public TextView txtHomeHistory;
    public TextView txtHomeSetting;
    private TextView txtHomeSaldo;
    private TextView txtHomeNama;
    private TextView txtHomeNoTelp;
    private TextView txtHomeEmail;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser curUser = mAuth.getCurrentUser();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("User").child(curUser.getUid());

    private ValueEventListener valueEvent;
    private User user;

    private static long back_pressed ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //variable declaration
        txtHomeSaldo = (TextView) findViewById(R.id.txtHomeSaldo);
        txtHomeNama = (TextView) findViewById(R.id.txtHomeNama);
        txtHomeNoTelp = (TextView) findViewById(R.id.txtHomeNoTelp);
        txtHomeEmail = (TextView) findViewById(R.id.txtHomeEmail);

        //view from firebase
        txtHomeNama.setText(curUser.getDisplayName());
        txtHomeEmail.setText(curUser.getEmail());
        txtHomeNoTelp.setText(curUser.getPhoneNumber());

        valueEvent = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                //user = new User(Double.valueOf(dataSnapshot.child("saldo").getValue().toString())); //pake constructor -> User(Double saldo)
                txtHomeSaldo.setText("Rp. "+ user.getSaldo().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabase.addValueEventListener(valueEvent);

        //variable declaration
        btnHomeTopUp = (ImageButton)findViewById(R.id.btnHomeTopUp);
        btnHomeWithdraw = (ImageButton)findViewById(R.id.btnHomeWithdraw);
        btnHomeScanQR = (ImageButton) findViewById(R.id.btnHomeScanQR);
        imgNavHomeSetting = (ImageView) findViewById(R.id.imgNavHomeSetting);
        imgNavHomeHistory = (ImageView) findViewById(R.id.imgNavHomeHistory);
        txtHomeSetting = (TextView) findViewById(R.id.txtHomeSetting);
        txtHomeHistory = (TextView) findViewById(R.id.txtHomeHistory);

        //on click function
        btnHomeTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, TopupActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        btnHomeWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, WithdrawActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        btnHomeScanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ScanQRActivity.class));
                //Intent intent = new Intent(HomeActivity.this, PaymentConfirmationActivity.class);
                //startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        imgNavHomeHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, HistoryActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        txtHomeHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, HistoryActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        imgNavHomeSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        txtHomeSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
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
