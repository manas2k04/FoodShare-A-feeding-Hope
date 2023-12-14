package com.manas.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RequestFormActivity extends AppCompatActivity {

    private DatabaseReference requestsRef;
    private static final int REQUEST_CODE_UPDATE_REWARD_POINTS = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_form);

        requestsRef = FirebaseDatabase.getInstance().getReference("requests");

        EditText etName = findViewById(R.id.etName);
        EditText etDescription = findViewById(R.id.etDescription);
        EditText etMembers = findViewById(R.id.etMembers);
        EditText etAddress = findViewById(R.id.etAddress);
        EditText etPhoneNumber = findViewById(R.id.etPhoneNumber);
        Button btnSubmitRequest = findViewById(R.id.btnSubmitRequest);
        Button btnBack = findViewById(R.id.btnBackToUser);

        btnSubmitRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the entered values
                String name = etName.getText().toString();
                String description = etDescription.getText().toString();
                String members = etMembers.getText().toString();
                String address = etAddress.getText().toString();
                String phoneNumber = etPhoneNumber.getText().toString(); // Added field

                // Validate the input fields (you can add more validation as needed)
                if (!name.isEmpty() && !description.isEmpty() && !members.isEmpty() && !address.isEmpty() && !phoneNumber.isEmpty()) {
                    // Create a Request object with the entered data
                    Request request = new Request(name, description, members, address, phoneNumber);

                    // Get a unique key for the request
                    String requestKey = requestsRef.push().getKey();

                    // Save the request data to the database
                    assert requestKey != null;
                    requestsRef.child(requestKey).setValue(request);

                    showToast("Request form submitted successfully");

                    // Update reward points
                    updateRewardPoints();

                    // You can add additional logic here if needed

                } else {
                    showToast("Please fill in all fields");
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void updateRewardPoints() {
        // Assume you have the logic to update reward points as mentioned earlier
        // For simplicity, I'm using an increment of 5
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userId = mAuth.getCurrentUser().getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(userId);

        userRef.child("rewardPoints").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int currentRewardPoints = dataSnapshot.getValue(Integer.class);
                    int updatedRewardPoints = currentRewardPoints + 5;

                    userRef.child("rewardPoints").setValue(updatedRewardPoints);

                    // Start RewardPoints activity
                    Intent rewardPointsIntent = new Intent(RequestFormActivity.this, RewardPoints.class);
                    rewardPointsIntent.putExtra("currentRewardPoints", updatedRewardPoints);
                    startActivityForResult(rewardPointsIntent, REQUEST_CODE_UPDATE_REWARD_POINTS);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_UPDATE_REWARD_POINTS && resultCode == RESULT_OK) {
            // Handle any logic after updating reward points, if needed
            int updatedRewardPoints = data.getIntExtra("updatedRewardPoints", 0);
            showToast("Updated Reward Points: " + updatedRewardPoints);
        }
    }
}
