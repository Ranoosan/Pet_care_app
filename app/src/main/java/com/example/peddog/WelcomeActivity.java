package com.example.peddog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        // Find the "Get Started" button
        MaterialCardView getStartedButton = findViewById(R.id.get_started_button);

        // Set an OnClickListener on the "Get Started" button
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to LoginActivity
                Intent intent = new Intent(WelcomeActivity.this, login.class);
                startActivity(intent);
            }
        });
    }
}
