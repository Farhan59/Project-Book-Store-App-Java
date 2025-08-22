package edu.ewubd.projectbookstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BuyBookActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BuyBookAdapter adapter;
    private List<Book> cartItems;
    private double totalFee;

    private EditText etName, etMobile, etCountryCity, etStreetBuilding;
    private TextView tvTotalFee;
    private Button btnBack, btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_book);

        // Get the cartItems from the intent
        ArrayList<Parcelable> parcelableCartItems = getIntent().getParcelableArrayListExtra("CART_ITEMS");
        if (parcelableCartItems != null) {
            cartItems = new ArrayList<>();
            for (Parcelable parcelable : parcelableCartItems) {
                if (parcelable instanceof Book) {
                    cartItems.add((Book) parcelable);
                }
            }
        } else {
            // Handle the case when cartItems is null or not present in the intent
            cartItems = new ArrayList<>();
        }

        // Initialize views
        recyclerView = findViewById(R.id.myRecycler);
        etName = findViewById(R.id.etName);
        etMobile = findViewById(R.id.etMobile);
        etCountryCity = findViewById(R.id.etCountryCity);
        etStreetBuilding = findViewById(R.id.etStreetBuilding);
        tvTotalFee = findViewById(R.id.tvTotalFee);
        btnBack = findViewById(R.id.btnBack);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Initialize RecyclerView and adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BuyBookAdapter(cartItems);
        recyclerView.setAdapter(adapter);

        // Calculate and set total fee
        calculateTotalFee();

        // Set click listeners for buttons
        btnBack.setOnClickListener(v -> finish());
        btnSubmit.setOnClickListener(v -> handleSubmitOrder());
    }

    private void calculateTotalFee() {
        double totalBookPrice = 0;
        for (Book book : cartItems) {
            totalBookPrice += book.getPrice();
        }
        totalFee = totalBookPrice + 60; // Add delivery fee (60 Taka)
        tvTotalFee.setText(String.format("%.2f Taka", totalFee));
    }


    private void handleSubmitOrder() {
        // Validate input fields
        if (validateFields()) {
            // Create intent for BuyConfirmActivity
            Intent intent = new Intent(this, BuyConfirmActivity.class);
            // Pass necessary data (cart items, total fee, user details)
            intent.putParcelableArrayListExtra("CART_ITEMS", new ArrayList<>(cartItems));
            intent.putExtra("TOTAL_FEE", totalFee);
            intent.putExtra("USER_NAME", etName.getText().toString());
            intent.putExtra("USER_MOBILE", etMobile.getText().toString());
            intent.putExtra("USER_COUNTRY_CITY", etCountryCity.getText().toString());
            intent.putExtra("USER_STREET_BUILDING", etStreetBuilding.getText().toString());

            // Start the BuyConfirmActivity before clearing the cart
            startActivity(intent);
            // Clear the cart
            CartManager.getInstance().clearCart();
            finish();
        }
    }

    private boolean validateFields() {
        boolean isValid = true;

        if (etName.getText().toString().trim().isEmpty()) {
            etName.setError("Please enter your name");
            isValid = false;
        }

        if (etMobile.getText().toString().trim().isEmpty()) {
            etMobile.setError("Please enter your mobile number");
            isValid = false;
        } else if (!etMobile.getText().toString().trim().matches("\\d{11}")) {
            etMobile.setError("Please enter a valid 11-digit mobile number");
            isValid = false;
        }

        if (etCountryCity.getText().toString().trim().isEmpty()) {
            etCountryCity.setError("Please enter your country and city");
            isValid = false;
        }

        if (etStreetBuilding.getText().toString().trim().isEmpty()) {
            etStreetBuilding.setError("Please enter your street and building");
            isValid = false;
        }

        if (!isValid) {
            Toast.makeText(this, "Please fill in all fields correctly", Toast.LENGTH_SHORT).show();
        }

        return isValid;
    }
}
