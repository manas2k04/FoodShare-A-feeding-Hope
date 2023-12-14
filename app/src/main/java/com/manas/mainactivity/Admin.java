// Admin.java
package com.manas.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin extends AppCompatActivity {

    private DatabaseReference donationsRef;
    private DatabaseReference requestsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        donationsRef = database.getReference("donations");
        requestsRef = database.getReference("requests");

        Button btnDonors = findViewById(R.id.btn_donors_address);
        Button btnRequestReceived = findViewById(R.id.btn_request_received);
        Button btnLogout = findViewById(R.id.btn_Logout);

        btnDonors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle Donors button click
                retrieveDonationData();
            }
        });

        btnRequestReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle Request Received button click
                retrieveRequestData();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle Logout button click
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Admin.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void retrieveRequestData() {
        requestsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Handle the data received from Firebase for request forms
                StringBuilder requestData = new StringBuilder();
                for (DataSnapshot requestSnapshot : dataSnapshot.getChildren()) {
                    Request request = requestSnapshot.getValue(Request.class);
                    if (request != null) {
                        requestData.append("Name: ").append(request.getName()).append("\n");
                        requestData.append("Description: ").append(request.getDescription()).append("\n");
                        requestData.append("Members: ").append(request.getMembers()).append("\n");
                        requestData.append("Address: ").append(request.getAddress()).append("\n");
                        requestData.append("Phone Number: ").append(request.getPhoneNumber()).append("\n\n");
                    }
                }

                // Check if requestData is not empty before displaying
                if (requestData.length() > 0) {
                    // Display the request data
                    displayRequestData(requestData.toString());
                } else {
                    showToast("No Request Data Found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });
    }

    private void displayRequestData(String data) {
        // Display the request data in a new activity
        Intent intent = new Intent(Admin.this, RequestDetailsActivity.class);
        intent.putExtra(RequestDetailsActivity.EXTRA_REQUEST_DETAILS, data);
        startActivity(intent);
    }

    private void retrieveDonationData() {
        donationsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Handle the data received from Firebase for donation forms
                StringBuilder donorData = new StringBuilder();
                for (DataSnapshot donationSnapshot : dataSnapshot.getChildren()) {
                    Donation donation = donationSnapshot.getValue(Donation.class);
                    if (donation != null) {
                        donorData.append("Name: ").append(donation.getName()).append("\n");
                        donorData.append("Food Name: ").append(donation.getFoodName()).append("\n");
                        donorData.append("Address: ").append(donation.getAddress()).append("\n\n");
                        donorData.append("PinCode: ").append(donation.getAddress()).append("\n\n");
                    }
                }

                // Display the donor data
                displayDonorData(donorData.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });
    }

    private void displayDonorData(String data) {
        // Display the donor data in a new activity
        Intent intent = new Intent(Admin.this, DonorDetailsActivity.class);
        intent.putExtra(DonorDetailsActivity.EXTRA_DONOR_DETAILS, data);
        startActivity(intent);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
