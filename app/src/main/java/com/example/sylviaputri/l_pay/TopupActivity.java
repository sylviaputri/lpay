package com.example.sylviaputri.l_pay;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class TopupActivity extends AppCompatActivity {
    public ImageButton btnTopUpBack;
    public Spinner dropdownBank;
    public String[] pilihanBank;
    public TextView txtInstruksiTransfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);


        btnTopUpBack = (ImageButton) findViewById(R.id.btnTopUpBack);
        btnTopUpBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TopupActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        dropdownBank = findViewById(R.id.spinnerWithdrawBank);
        pilihanBank = new String[]{"Choose Bank", "Bank BCA", "Bank BRI", "Bank BNI", "Bank Mandiri"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pilihanBank);
        dropdownBank.setAdapter(adapter);
    }
}
