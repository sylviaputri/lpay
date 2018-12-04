package com.example.sylviaputri.l_pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class WithdrawActivity extends AppCompatActivity {
    public ImageButton btnWithdrawBack;
    public Button btnWithdrawWithdraw;
    public EditText txtWithdrawAmount;
    public TextView txtWithdrawSaldo;
    public Spinner dropdownBank;
    public String[] pilihanBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        btnWithdrawBack = (ImageButton) findViewById(R.id.btnWithdrawBack);
        btnWithdrawWithdraw = (Button) findViewById(R.id.btnWithdrawWithdraw);

        btnWithdrawBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WithdrawActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnWithdrawWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WithdrawActivity.this, EnterPinActivity.class);
                startActivity(intent);
            }
        });

        txtWithdrawAmount = (EditText) findViewById(R.id.txtWithdrawAmount);
        txtWithdrawSaldo = (TextView) findViewById(R.id.txtWithdrawSaldo);

        dropdownBank = findViewById(R.id.spinnerWithdrawBank);
        pilihanBank = new String[]{"Choose Bank", "Bank BCA", "Bank BRI", "Bank BNI", "Bank Mandiri"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pilihanBank);
        dropdownBank.setAdapter(adapter);
    }
}
