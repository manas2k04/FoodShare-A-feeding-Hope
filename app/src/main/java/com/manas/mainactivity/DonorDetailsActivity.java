package com.manas.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DonorDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_DONOR_DETAILS = "extra_donor_details";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donors_details);

        TableLayout tableLayout = findViewById(R.id.tableLayout);
        Button backButton = findViewById(R.id.backButton);

        // Retrieve donor details from the intent
        if (getIntent().hasExtra(EXTRA_DONOR_DETAILS)) {
            String donorDetails = getIntent().getStringExtra(EXTRA_DONOR_DETAILS);

            // Display the donor details in a table format
            String[] detailsArray = donorDetails.split(";");
            TableRow row = new TableRow(this);

            for (String detail : detailsArray) {
                TextView textView = new TextView(this);
                textView.setText(detail);
                textView.setPadding(16, 16, 16, 16);
                row.addView(textView);
            }

            tableLayout.addView(row);
        }

        // Set click listener for the "Back" button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to the admin screen
                Intent intent = new Intent(DonorDetailsActivity.this, Admin.class);
                startActivity(intent);
                finish(); // Finish the current activity
            }
        });
    }
}
