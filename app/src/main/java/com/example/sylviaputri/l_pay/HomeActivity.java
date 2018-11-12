package com.example.sylviaputri.l_pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {
    public ImageButton btnHomeTopUp;
    public ImageButton btnHomeWithdraw;
    public ImageButton btnHomeScanQR;
    public ImageView imgNavHomeSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnHomeTopUp = (ImageButton)findViewById(R.id.btnHomeTopUp);
        btnHomeWithdraw = (ImageButton)findViewById(R.id.btnHomeWithdraw);
        btnHomeScanQR = (ImageButton) findViewById(R.id.btnHomeScanQR);
        imgNavHomeSetting = (ImageView) findViewById(R.id.imgNavHomeSetting);

        btnHomeTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, TopupActivity.class);
                startActivity(intent);
            }
        });

        btnHomeWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, WithdrawActivity.class);
                startActivity(intent);
            }
        });

        btnHomeScanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ScanQRActivity.class);
                startActivity(intent);
            }
        });

        imgNavHomeSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }
}
