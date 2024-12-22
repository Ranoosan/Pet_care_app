package com.example.peddog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Authenticate extends AppCompatActivity {
    FirebaseAuth auth;
    Button logoutButton, viewProductsButton, profileButton;
    TextView userDetailsTextView;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authenticate);

        auth = FirebaseAuth.getInstance();
        logoutButton = findViewById(R.id.logout);
        viewProductsButton = findViewById(R.id.products);
//        profileButton = findViewById(R.id.profile);
        userDetailsTextView = findViewById(R.id.user_details);

        user = auth.getCurrentUser();

        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), login.class);
            startActivity(intent);
            finish();
        } else {
            userDetailsTextView.setText(user.getEmail());
        }

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });

        viewProductsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

//        profileButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    public void onHomeClick(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void onUserProfileClick(View view) {
        Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
        startActivity(intent);
    }
}
