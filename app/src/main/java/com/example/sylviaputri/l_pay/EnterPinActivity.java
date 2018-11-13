package com.example.sylviaputri.l_pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class EnterPinActivity extends AppCompatActivity {

    public ImageView btnEnterpinOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterpin);

        btnEnterpinOk = (ImageView) findViewById(R.id.btnEnterpinOk);

        btnEnterpinOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnterPinActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


    }
}
