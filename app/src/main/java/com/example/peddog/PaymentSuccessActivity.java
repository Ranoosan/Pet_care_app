package com.example.peddog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentSuccessActivity extends AppCompatActivity {

    private TextView referenceNumberView;
    private TextView dateView;
    private TextView timeView;
    private TextView paymentMethodView;
    private TextView amountView;
    private Button buttonGetPdfReceipt;
    private Button buttonGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);

        referenceNumberView = findViewById(R.id.reference_number);
        dateView = findViewById(R.id.date);
        timeView = findViewById(R.id.time);
        paymentMethodView = findViewById(R.id.payment_method);
        amountView = findViewById(R.id.amount);
        buttonGetPdfReceipt = findViewById(R.id.button_get_pdf_receipt);
        buttonGoBack = findViewById(R.id.goback);  // Updated line

        // Retrieve data from the Intent
        Intent intent = getIntent();
        String referenceNumber = intent.getStringExtra("reference_number");
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");
        String paymentMethod = intent.getStringExtra("payment_method");
        String amount = intent.getStringExtra("amount");

        // Set the data to the views
        referenceNumberView.setText(referenceNumber);
        dateView.setText(date);
        timeView.setText(time);
        paymentMethodView.setText(paymentMethod);
        amountView.setText(amount);

        // Set click listener for the PDF receipt button
        buttonGetPdfReceipt.setOnClickListener(v -> {
            // Handle PDF receipt generation logic here
            // For example, you can create a PDF and save it or share it
        });

        // Set click listener for the Go Back button
        buttonGoBack.setOnClickListener(v -> {
            Intent goBackIntent = new Intent(PaymentSuccessActivity.this, MainActivity2.class);
            startActivity(goBackIntent);
        });
    }
}
