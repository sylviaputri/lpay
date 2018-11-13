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

public class HomeActivity extends AppCompatActivity {
    public ImageButton btnHomeTopUp;
    public ImageButton btnHomeWithdraw;
    public ImageButton btnHomeScanQR;
    public ImageView imgNavHomeSetting;
    public ImageView imgNavHomeHistory;
    public TextView txtHomeHome;
    public TextView txtHomeHistory;
    public TextView txtHomeSetting;

    private static long back_pressed ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnHomeTopUp = (ImageButton)findViewById(R.id.btnHomeTopUp);
        btnHomeWithdraw = (ImageButton)findViewById(R.id.btnHomeWithdraw);
        btnHomeScanQR = (ImageButton) findViewById(R.id.btnHomeScanQR);
        imgNavHomeSetting = (ImageView) findViewById(R.id.imgNavHomeSetting);
        imgNavHomeHistory = (ImageView) findViewById(R.id.imgNavHomeHistory);
        txtHomeSetting = (TextView) findViewById(R.id.txtHomeSetting);
        txtHomeHistory = (TextView) findViewById(R.id.txtHomeHistory);

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

        imgNavHomeHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

        txtHomeHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, HistoryActivity.class);
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
        txtHomeSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(intent);
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
