package com.example.sylviaputri.l_pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
    public ImageView imgNavSettingHome;
    public ImageView imgNavSettingHistory;
    public TextView txtSettingHistory;
    public TextView txtSettingHome;
    public Button btnSettingLogout;
    public Button btnSettingPassword;
    public Button btnSettingProfile;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SettingActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        imgNavSettingHome = (ImageView) findViewById(R.id.imgNavSettingHome);
        imgNavSettingHistory = (ImageView) findViewById(R.id.imgNavSettingHistory);
        txtSettingHome = (TextView) findViewById(R.id.txtSettingHome);
        txtSettingHistory = (TextView) findViewById(R.id.txtSettingHistory);
        btnSettingLogout = (Button) findViewById(R.id.btnSettingLogout);
        btnSettingPassword = (Button) findViewById(R.id.btnSettingPassword);
        btnSettingProfile = (Button) findViewById(R.id.btnSettingProfile);

        imgNavSettingHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        txtSettingHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        imgNavSettingHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
        txtSettingHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

        btnSettingLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnSettingProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, SettingProfilActivity.class);
                startActivity(intent);
            }
        });
        btnSettingPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, SettingPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
