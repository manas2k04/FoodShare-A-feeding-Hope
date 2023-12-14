package com.manas.mainactivity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        // Initialize TextViews with telephone numbers and email IDs
        TextView phoneNumber1 = findViewById(R.id.phoneNumber1);
        TextView phoneNumber2 = findViewById(R.id.phoneNumber2);
        TextView email1 = findViewById(R.id.email1);
        TextView email2 = findViewById(R.id.email2);

        // Set the contact information
        phoneNumber1.setText("9347684374");
        phoneNumber2.setText("8688738281");
        email1.setText("mchhatwal7@gmail.com");
        email2.setText("21h51a0511@cmrcet.ac.in");
    }
}
