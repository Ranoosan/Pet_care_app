package com.example.peddog;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class checkout extends AppCompatActivity {

    private EditText etCardHolderName, etCardNumber, etExpiryDate, etCVV;
    private Button btnConfirmPayment;
    private ImageView btnBack;
    private String totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);

        etCardHolderName = findViewById(R.id.et_card_holder_name);
        etCardNumber = findViewById(R.id.et_card_number);
        etExpiryDate = findViewById(R.id.et_expiry_date);
        etCVV = findViewById(R.id.et_cvv);
        btnConfirmPayment = findViewById(R.id.btn_confirm_payment);
        btnBack = findViewById(R.id.btn_back);

        // Retrieve total amount from the Intent
        totalAmount = getIntent().getStringExtra("total_amount");

        btnBack.setOnClickListener(v -> finish());

        btnConfirmPayment.setOnClickListener(v -> processPayment());
    }

    private void processPayment() {
        String cardHolderName = etCardHolderName.getText().toString().trim();
        String cardNumber = etCardNumber.getText().toString().trim();
        String expiryDate = etExpiryDate.getText().toString().trim();
        String cvv = etCVV.getText().toString().trim();

        if (TextUtils.isEmpty(cardHolderName) || TextUtils.isEmpty(cardNumber) || TextUtils.isEmpty(expiryDate) || TextUtils.isEmpty(cvv)) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cardNumber.length() != 16 || !TextUtils.isDigitsOnly(cardNumber)) {
            Toast.makeText(this, "Card number must be 16 digits", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cvv.length() != 3 || !TextUtils.isDigitsOnly(cvv)) {
            Toast.makeText(this, "CVV must be 3 digits", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isExpiryDateValid(expiryDate)) {
            Toast.makeText(this, "Expiry date must be in the format MM/YY and in the future", Toast.LENGTH_SHORT).show();
            return;
        }

        // Assuming payment is successful, redirect to PaymentSuccessActivity
        Intent intent = new Intent(checkout.this, PaymentSuccessActivity.class);
        intent.putExtra("reference_number", "000085752257");

        // Get the current date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String date = dateFormat.format(new Date());
        String time = timeFormat.format(new Date());

        intent.putExtra("date", date);
        intent.putExtra("time", time);
        intent.putExtra("payment_method", "Credit Card");
        intent.putExtra("amount", totalAmount);  // Pass the total amount
        startActivity(intent);

        // Show a toast message for successful payment
        Toast.makeText(this, "Payment successful", Toast.LENGTH_SHORT).show();
    }

    private boolean isExpiryDateValid(String expiryDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yy", Locale.getDefault());
        sdf.setLenient(false);

        try {
            Date date = sdf.parse(expiryDate);
            return date != null && date.after(new Date());
        } catch (ParseException e) {
            return false;
        }
    }
}
