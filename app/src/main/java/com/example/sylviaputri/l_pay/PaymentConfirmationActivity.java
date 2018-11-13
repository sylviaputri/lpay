package com.example.sylviaputri.l_pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PaymentConfirmationActivity extends AppCompatActivity {
    public Button btnPaymentConfCancel;
    public Button btnPaymentConfMakePayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirmation);

        btnPaymentConfCancel = (Button) findViewById(R.id.btnPaymentConfCancel);
        btnPaymentConfMakePayment = (Button) findViewById(R.id.btnPaymentConfMakePayment);

        btnPaymentConfCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentConfirmationActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnPaymentConfMakePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentConfirmationActivity.this, EnterPinActivity.class);
                startActivity(intent);
            }
        });
    }
}
