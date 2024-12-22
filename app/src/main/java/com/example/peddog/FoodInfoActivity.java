package com.example.peddog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class FoodInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);

        // Additional setup if needed
    }

    // Method to handle home button clicks
    public void onHomeClick(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
        startActivity(intent);
    }

    // Method to handle user profile button clicks
    public void onUserProfileClick(View view) {
        Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
        startActivity(intent);
    }

    // Method to handle info button clicks
    public void onInfoClick(View view) {
        Intent intent = new Intent(getApplicationContext(), FoodInfoActivity.class);
        startActivity(intent);
    }
}
