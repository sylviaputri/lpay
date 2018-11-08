package com.example.sylviaputri.l_pay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {
    public FrameLayout frmLoginBackground;
    public ImageView imgLoginLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        frmLoginBackground = findViewById(R.id.frmLoginBackground);
        imgLoginLogo = findViewById(R.id.imgLoginLogo);


    }
}
