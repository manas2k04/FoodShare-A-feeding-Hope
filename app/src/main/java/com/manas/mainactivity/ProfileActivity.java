package com.manas.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button btnHistory = findViewById(R.id.btnHistory);
        Button btnRewardPoints = findViewById(R.id.btnRewardPoints);
        Button btnContactUs = findViewById(R.id.btnContactUs);
        Button btnBack = findViewById(R.id.btnBack);

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add logic for the History button click
                Intent historyIntent = new Intent(ProfileActivity.this, HistoryActivity.class);
                startActivity(historyIntent);
            }
        });

        btnRewardPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle Reward Points button click
                Intent rewardPointsIntent = new Intent(ProfileActivity.this, RewardPoints.class);
                startActivity(rewardPointsIntent);
            }
        });


        btnContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, ContactUs.class);
                startActivity(intent);
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Close the ProfileActivity and go back to the previous activity (User)
                finish();
            }
        });
    }
}
