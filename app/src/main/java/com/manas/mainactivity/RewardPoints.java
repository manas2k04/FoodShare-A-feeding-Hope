package com.manas.mainactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RewardPoints extends AppCompatActivity {

    private static final int REQUEST_CODE_DONATION_FORMS = 1;

    private int currentRewardPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_points);

        TextView tvRewardPoints = findViewById(R.id.tvRewardPoints);
        Button btnDonate = findViewById(R.id.btnDonate);
        Button btnBack = findViewById(R.id.btnBack);

        // Retrieve current reward points from the intent
        currentRewardPoints = getIntent().getIntExtra("currentRewardPoints", 0);

        tvRewardPoints.setText("Your Reward Points: " + currentRewardPoints);

        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Increment the reward points by 50 when the "Donate" button is clicked
                currentRewardPoints += 50;

                // Update the TextView to display the new reward points
                tvRewardPoints.setText("Your Reward Points: " + currentRewardPoints);

                // Optionally, you can log the updated reward points
                Log.d("RewardPointsActivity", "Updated reward points: " + currentRewardPoints);

                // Launch the donate form activity
                Intent donateIntent = new Intent(RewardPoints.this, DonationForms.class);
                startActivityForResult(donateIntent, REQUEST_CODE_DONATION_FORMS);
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the result and finish the activity with the updated reward points
                Intent resultIntent = new Intent();
                resultIntent.putExtra("updatedRewardPoints", currentRewardPoints);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_DONATION_FORMS && resultCode == Activity.RESULT_OK) {
            // Donation form completed, update reward points by 5
            currentRewardPoints += 5;

            TextView tvRewardPoints = findViewById(R.id.tvRewardPoints);
            // Check if the TextView is null
            if (tvRewardPoints != null) {
                tvRewardPoints.setText("Your Reward Points: " + currentRewardPoints);
            } else {
                // Log an error if TextView is null
                // This should not happen if your layout is correctly defined
                // and the view IDs are used correctly
                android.util.Log.e("RewardPointsActivity", "TextView is null after donation form completion.");
            }
        }
    }
}
