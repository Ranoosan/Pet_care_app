package com.example.peddog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    private LinearLayout cartContainer;
    private TextView totalAmountView;
    private RadioGroup radioGroupPayment;
    private Button buttonCheckout;
    private Button buttonCancel;
    private Map<String, Integer> cart = new HashMap<>();
    private Map<String, Integer> prices = new HashMap<>(); // Map to store item prices

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartContainer = findViewById(R.id.cart_container);
        totalAmountView = findViewById(R.id.total_amount);
        radioGroupPayment = findViewById(R.id.radio_group_payment);
        buttonCheckout = findViewById(R.id.button_checkout);
        buttonCancel = findViewById(R.id.button_cancel);

        // Retrieve cart data from the Intent
        Bundle bundle = getIntent().getBundleExtra("cart_data");
        if (bundle != null) {
            for (String key : bundle.keySet()) {
                cart.put(key, bundle.getInt(key));
            }
        }

        // Initialize item prices (example data)
        prices.put("Royal Canin", 50);
        prices.put("Hill's Science", 60);
        prices.put("Kibble", 30);
        prices.put("Premium Crunch", 40);

        // Populate the cart
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            addItemToCart(entry.getKey(), entry.getValue());
        }

        // Set click listener for checkout button
        buttonCheckout.setOnClickListener(v -> {
            int selectedPaymentId = radioGroupPayment.getCheckedRadioButtonId();
            if (selectedPaymentId == -1) {
                Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show();
            } else {
                RadioButton selectedPaymentMethod = findViewById(selectedPaymentId);
                String paymentMethod = selectedPaymentMethod.getText().toString();
                // Pass the total amount to the checkout activity
                Intent intent = new Intent(CartActivity.this, checkout.class);
                intent.putExtra("total_amount", calculateTotalAmount());
                startActivity(intent);
            }
        });

        // Set click listener for cancel button
        buttonCancel.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, MainActivity2.class);
            startActivity(intent);
        });

        // Update total amount initially
        updateTotalAmount();
    }

    private void addItemToCart(String itemName, int quantity) {
        View itemView = getLayoutInflater().inflate(R.layout.cart_item, null);
        ImageView itemImage = itemView.findViewById(R.id.item_image);
        TextView itemNameView = itemView.findViewById(R.id.item_name);
        TextView itemDescriptionView = itemView.findViewById(R.id.item_description);
        TextView itemPriceView = itemView.findViewById(R.id.item_price);
        TextView itemQuantityView = itemView.findViewById(R.id.item_quantity);
        Button buttonDecrease = itemView.findViewById(R.id.button_decrease);
        Button buttonIncrease = itemView.findViewById(R.id.button_increase);
        Button buttonRemove = itemView.findViewById(R.id.button_remove);

        // Set item details
        itemNameView.setText(itemName);
        itemDescriptionView.setText("Description for " + itemName); // Example description
        int itemPrice = prices.get(itemName); // Get price from the map
        itemPriceView.setText("$" + itemPrice);
        itemQuantityView.setText(String.valueOf(quantity));

        // Handle button clicks
        buttonDecrease.setOnClickListener(v -> {
            int currentQuantity = Integer.parseInt(itemQuantityView.getText().toString());
            if (currentQuantity > 1) {
                currentQuantity--;
                itemQuantityView.setText(String.valueOf(currentQuantity));
                cart.put(itemName, currentQuantity);
                updateTotalAmount();
            }
        });

        buttonIncrease.setOnClickListener(v -> {
            int currentQuantity = Integer.parseInt(itemQuantityView.getText().toString());
            currentQuantity++;
            itemQuantityView.setText(String.valueOf(currentQuantity));
            cart.put(itemName, currentQuantity);
            updateTotalAmount();
        });

        buttonRemove.setOnClickListener(v -> {
            cart.remove(itemName);
            cartContainer.removeView(itemView);
            updateTotalAmount();
            Toast.makeText(this, itemName + " removed from cart", Toast.LENGTH_SHORT).show();
        });

        cartContainer.addView(itemView);
    }

    private void updateTotalAmount() {
        String totalAmount = calculateTotalAmount();
        totalAmountView.setText(totalAmount);
    }

    private String calculateTotalAmount() {
        int totalAmount = 0;
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            int price = prices.get(itemName);
            totalAmount += quantity * price;
        }
        return "Total amount: $" + totalAmount;
    }
}
