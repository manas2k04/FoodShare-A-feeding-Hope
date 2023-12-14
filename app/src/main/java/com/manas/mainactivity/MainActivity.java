package com.manas.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        // Check if the user is not logged in
        if (user == null) {
            // If the user is not logged in, redirect to the LoginActivity
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish(); // Optional: Close the MainActivity to prevent going back
        } else {
            // If the user is logged in, redirect to the Interface activity
            Intent intent = new Intent(getApplicationContext(), Interface.class);
            startActivity(intent);
             // Optional: Close the MainActivity to prevent going back
        }
    }
}





