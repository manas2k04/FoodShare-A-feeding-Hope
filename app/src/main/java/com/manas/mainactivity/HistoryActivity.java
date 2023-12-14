package com.manas.mainactivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HistoryActivity extends AppCompatActivity {
    private DatabaseReference donationsRef;
    private DatabaseReference requestsRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();

            DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
            donationsRef = databaseRef.child("donations").child(userId);
            requestsRef = databaseRef.child("requests").child(userId);

            retrieveDonationData();
            retrieveRequestData();
        } else {
            // Handle the case where the user is null
            // This may occur if the user is not authenticated
            // You might want to redirect the user to the login screen or handle it appropriately
            finish(); // Close the activity if the user is not authenticated
        }

        // Back Button
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> onBackPressed());
    }

    private void retrieveDonationData() {
        donationsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Handle the data received from Firebase for donation details
                StringBuilder donationData = new StringBuilder();
                for (DataSnapshot donationSnapshot : dataSnapshot.getChildren()) {
                    Donation donation = donationSnapshot.getValue(Donation.class);
                    if (donation != null) {
                        donationData.append("Food Name: ").append(donation.getFoodName()).append("\n");
                        donationData.append("Address: ").append(donation.getAddress()).append("\n\n");
                        // Add more fields as needed
                    }
                }

                // Display the donation data
                displayDonationData(donationData.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });
    }

    private void retrieveRequestData() {
        requestsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Handle the data received from Firebase for request details
                StringBuilder requestData = new StringBuilder();
                for (DataSnapshot requestSnapshot : dataSnapshot.getChildren()) {
                    Request request = requestSnapshot.getValue(Request.class);
                    if (request != null) {
                        requestData.append("Description: ").append(request.getDescription()).append("\n");
                        requestData.append("Members: ").append(request.getMembers()).append("\n");
                        requestData.append("Address: ").append(request.getAddress()).append("\n\n");
                        // Add more fields as needed
                    }
                }

                // Display the request data
                displayRequestData(requestData.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });
    }

    private void displayDonationData(String data) {
        TextView donationDetailsTextView = findViewById(R.id.donationDetailsTextView);
        donationDetailsTextView.setText(data);
    }

    private void displayRequestData(String data) {
        TextView requestDetailsTextView = findViewById(R.id.requestDetailsTextView);
        requestDetailsTextView.setText(data);
    }
}
