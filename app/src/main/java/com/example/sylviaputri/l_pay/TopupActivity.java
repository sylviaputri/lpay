package com.example.sylviaputri.l_pay;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class TopupActivity extends AppCompatActivity {
    public ImageButton btnTopUpBack;

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
    }
}
