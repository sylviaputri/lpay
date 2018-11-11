package com.example.sylviaputri.l_pay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegistrationActivity extends AppCompatActivity {
    public Button btnRegisRegister;
    public Button btnRegisLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btnRegisLogin = (Button) findViewById(R.id.btnRegisLogin);
        btnRegisRegister = (Button) findViewById(R.id.btnRegisRegister);

        btnRegisLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegisRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
