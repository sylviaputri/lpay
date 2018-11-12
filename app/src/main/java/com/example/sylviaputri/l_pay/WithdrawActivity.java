package com.example.sylviaputri.l_pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class WithdrawActivity extends AppCompatActivity {
    public ImageButton btnWithdrawBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        btnWithdrawBack = (ImageButton) findViewById(R.id.btnWithdrawBack);

        btnWithdrawBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WithdrawActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
