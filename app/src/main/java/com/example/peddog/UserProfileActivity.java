package com.example.peddog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfileActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextAddress;
    private Spinner spinnerPaymentMethod;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        editTextName = findViewById(R.id.edit_text_name);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextAddress = findViewById(R.id.edit_text_address);
        spinnerPaymentMethod = findViewById(R.id.spinner_payment_method);
        buttonSave = findViewById(R.id.button_save);
        Button backToMenuButton = findViewById(R.id.button_back_to_menu);

        // Set email as non-editable and display user's email
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            editTextEmail.setText(user.getEmail());
            editTextEmail.setEnabled(false);
        }

        // Populate the Spinner with payment method options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.payment_methods, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPaymentMethod.setAdapter(adapter);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user profile details
                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String address = editTextAddress.getText().toString().trim();
                String paymentMethod = spinnerPaymentMethod.getSelectedItem().toString();

                // Perform validation
                if (name.isEmpty()) {
                    editTextName.setError("Name is required");
                    editTextName.requestFocus();
                    return;
                }

                if (address.isEmpty()) {
                    editTextAddress.setError("Address is required");
                    editTextAddress.requestFocus();
                    return;
                }

                // Show saved details in a Toast
                String message = String.format("Name: %s\nEmail: %s\nAddress: %s\nPayment Method: %s",
                        name, email, address, paymentMethod);
                Toast.makeText(UserProfileActivity.this, message, Toast.LENGTH_LONG).show();

                // Save to database or preferences (implementation needed here)
            }
        });

        backToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, Authenticate.class);
                startActivity(intent);
            }
        });
    }
}
