package com.manas.mainactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DonationForms extends AppCompatActivity {

    private DatabaseReference donationsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donationforms);

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        donationsRef = database.getReference("donations");

        final EditText etName = findViewById(R.id.editTextFullName);
        final EditText etFoodName = findViewById(R.id.editTextFoodName);
        final EditText etAddress = findViewById(R.id.editTextAddress);
        final EditText etArea = findViewById(R.id.editTextArea);

        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the entered values
                String name = etName.getText().toString();
                String foodName = etFoodName.getText().toString();
                String address = etAddress.getText().toString();
                String area = etArea.getText().toString();

                // Validate the input fields (you can add more validation as needed)
                if (!name.isEmpty() && !foodName.isEmpty() && !address.isEmpty() && !area.isEmpty()) {
                    // Create a Donation object with the entered data
                    Donation donation = new Donation(name, foodName, address, area);

                    // Get a unique key for the donation
                    String donationKey = donationsRef.push().getKey();

                    // Save the donation data to the database
                    assert donationKey != null;
                    donationsRef.child(donationKey).setValue(donation);

                    showToast("Donation form data submitted successfully");

                    // Update reward points by 5 (you need to implement a method to update reward points)
                    updateRewardPoints(5);

                    // Do not finish(); // Keep the form activity open
                } else {
                    showToast("Please fill in all fields");
                }
            }
        });

        Button btnBackToUser = findViewById(R.id.btnBackToUser);
        btnBackToUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the "Back to User" button click
                finish(); // Close the form activity when "Back to User" is clicked
            }
        });
    }

    // Helper method to show a toast message
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Method to update reward points (you need to implement this based on your logic)
    private void updateRewardPoints(int points) {
        // Implement your logic to update reward points
        // For example, you can use Firebase Realtime Database to store and retrieve reward points
        // This method should be adapted based on your application's architecture
    }
}
