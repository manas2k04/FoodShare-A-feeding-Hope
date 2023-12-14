package com.manas.mainactivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RequestDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_REQUEST_DETAILS = "extra_request_details";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);

        TableLayout tableLayout = findViewById(R.id.tableLayout);
        Button backButton = findViewById(R.id.backButton);

        // Get the request details from the intent
        String requestDetails = getIntent().getStringExtra(EXTRA_REQUEST_DETAILS);

        // Display the request details in a table format
        if (requestDetails != null) {
            String[] detailsArray = requestDetails.split(";");

            for (String detail : detailsArray) {
                TableRow row = new TableRow(this);
                TextView textView = new TextView(this);

                textView.setText(detail);
                textView.setPadding(16, 16, 16, 16);
                textView.setGravity(Gravity.CENTER);

                row.addView(textView);
                tableLayout.addView(row);
            }
        }

        // Add onClickListener for the back button
        backButton.setOnClickListener(view -> finish());
    }
}


