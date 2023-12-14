package com.manas.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class User extends AppCompatActivity {

    private static final int REQUEST_CODE_REWARD_POINTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Button btnDonate = findViewById(R.id.btnDonate);
        Button btnRequest = findViewById(R.id.btnRequest);
        Button btnProfile = findViewById(R.id.btnProfile);
        Button btnLogout = findViewById(R.id.btnLogout);
        Button btnBack = findViewById(R.id.btnBack);

        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Donate button click
                // Navigate to the DonationForms activity
                Intent donationFormsIntent = new Intent(User.this, DonationForms.class);
                startActivityForResult(donationFormsIntent, REQUEST_CODE_REWARD_POINTS);
            }
        });

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Request button click
                // Start the RequestFormActivity and wait for the result
                Intent requestFormIntent = new Intent(User.this, RequestFormActivity.class);
                startActivity(requestFormIntent);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Profile button click
                Intent profileIntent = new Intent(User.this, ProfileActivity.class);
                startActivity(profileIntent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Logout button click
                FirebaseAuth.getInstance().signOut();

                // Navigate to the LoginActivity (or any other desired destination)
                Intent intent = new Intent(User.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Back button click
                onBackPressed();
            }
        });
    }

    // Override onActivityResult to handle the result from RewardPoints or DonationForms activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_REWARD_POINTS && resultCode == RESULT_OK && data != null) {
            int updatedRewardPoints = data.getIntExtra("updatedRewardPoints", 0);
            updateRewardPoints(updatedRewardPoints);
        }
    }

    private void updateRewardPoints(int newRewardPoints) {
        TextView tvRewardPoints = findViewById(R.id.tvRewardPoints);
        tvRewardPoints.setText("Your Reward Points: " + newRewardPoints);
    }
}
