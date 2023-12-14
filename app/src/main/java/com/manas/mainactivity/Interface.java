package com.manas.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Interface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);

        Button btnUser = findViewById(R.id.btn_user);
        Button btnAdmin = findViewById(R.id.btn_admin);

        btnUser.setOnClickListener(v -> {
            // Handle User button click
            Intent intent = new Intent(Interface.this, User.class);
            startActivity(intent);
        });

        btnAdmin.setOnClickListener(v -> {
            // Handle Admin button click
            Intent intent = new Intent(Interface.this, Admin.class);
            startActivity(intent);
        });
    }
}
