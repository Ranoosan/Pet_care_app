package com.example.peddog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    private LinearLayout wetFoodSection;
    private LinearLayout dryFoodSection;
    private Map<String, Integer> cart = new HashMap<>();
    private Button buttonAddToCart1, buttonAddToCart2, buttonAddToCart3, buttonAddToCart4, buttonCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize UI components
        Button buttonWetFood = findViewById(R.id.button_wet_food);
        Button buttonDryFood = findViewById(R.id.button_dry_food);
        wetFoodSection = findViewById(R.id.wet_food_section);
        dryFoodSection = findViewById(R.id.dry_food_section);

        // Initialize cart buttons
        buttonAddToCart1 = findViewById(R.id.button_add_to_cart_1);
        buttonAddToCart2 = findViewById(R.id.button_add_to_cart_2);
        buttonAddToCart3 = findViewById(R.id.button_add_to_cart_3);
        buttonAddToCart4 = findViewById(R.id.button_add_to_cart_4);
        buttonCart = findViewById(R.id.button_cart);

        // Set click listeners for section buttons
        buttonWetFood.setOnClickListener(v -> showWetFoodSection());
        buttonDryFood.setOnClickListener(v -> showDryFoodSection());

        // Set click listeners for add-to-cart buttons
        buttonAddToCart1.setOnClickListener(v -> addToCart("Royal Canin", ((Spinner) findViewById(R.id.spinner_quantity_1)).getSelectedItemPosition()));
        buttonAddToCart2.setOnClickListener(v -> addToCart("Hill's Science", ((Spinner) findViewById(R.id.spinner_quantity_2)).getSelectedItemPosition()));
        buttonAddToCart3.setOnClickListener(v -> addToCart("Kibble", ((Spinner) findViewById(R.id.spinner_quantity_3)).getSelectedItemPosition()));
        buttonAddToCart4.setOnClickListener(v -> addToCart("Premium Crunch", ((Spinner) findViewById(R.id.spinner_quantity_4)).getSelectedItemPosition()));

        // Set click listener for cart button
        buttonCart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, CartActivity.class);
            Bundle bundle = new Bundle();
            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                bundle.putInt(entry.getKey(), entry.getValue());
            }
            intent.putExtra("cart_data", bundle); // Pass the cart data to CartActivity
            startActivity(intent);
        });
    }

    private void showWetFoodSection() {
        wetFoodSection.setVisibility(View.VISIBLE);
        dryFoodSection.setVisibility(View.GONE);
    }

    private void showDryFoodSection() {
        wetFoodSection.setVisibility(View.GONE);
        dryFoodSection.setVisibility(View.VISIBLE);
    }

    private void addToCart(String itemName, int quantity) {
        if (quantity > 0) {
            cart.put(itemName, quantity);
            Toast.makeText(this, itemName + " added to cart", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please select a quantity", Toast.LENGTH_SHORT).show();
        }
    }

    public void onHomeClick(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void onUserProfileClick(View view) {
        Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
        startActivity(intent);
    }
    public void oninfoClick(View view) {
        Intent intent = new Intent(getApplicationContext(), FoodInfoActivity.class);
        startActivity(intent);

    }
}