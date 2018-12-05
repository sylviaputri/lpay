package com.example.sylviaputri.l_pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HistoryActivity extends AppCompatActivity {
    public ImageView imgNavHistoryHome;
    public ImageView imgNavHistorySetting;
    public TextView txtHistoryHome;
    public TextView txtHistorSetting;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HistoryActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        imgNavHistoryHome = (ImageView) findViewById(R.id.imgNavHistoryHome);
        imgNavHistorySetting = (ImageView) findViewById(R.id.imgNavHistorySetting);
        txtHistoryHome = (TextView) findViewById(R.id.txtHistoryHome);
        txtHistorSetting = (TextView) findViewById(R.id.txtHistorySetting);

        imgNavHistoryHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        txtHistoryHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        imgNavHistorySetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
        txtHistorSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new HistoryJualBeliFragment(), "Jual Beli");
        adapter.addFragment(new HistoryWithdrawFragment(), "Withdraw");
        viewPager.setAdapter(adapter);
    }
}
