package edu.ewubd.projectbookstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<Book> cartList;
    private Button btnClear;
    private Button btnBack;
    private Button btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Initialize views
        recyclerView = findViewById(R.id.myRecycler);
        btnClear = findViewById(R.id.btnClear);
        btnBack = findViewById(R.id.btnBack);
        btnCheckout = findViewById(R.id.btnCheckout);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize cartList with items from CartManager
        cartList = CartManager.getInstance().getCartItems();

        // Set up RecyclerView and Adapter
        cartAdapter = new CartAdapter(cartList);
        recyclerView.setAdapter(cartAdapter);

        // Button click listeners
        btnClear.setOnClickListener(v -> clearCart());
        btnBack.setOnClickListener(v -> finish());

        btnCheckout.setOnClickListener(v -> {
            // Check if the cartList is not empty
            if (!cartList.isEmpty()) {
                // Create an intent to start the BuyBookActivity
                Intent intent = new Intent(CartActivity.this, BuyBookActivity.class);
                // Pass the cart items to the BuyBookActivity using an ArrayList of Parcelable objects
                intent.putParcelableArrayListExtra("CART_ITEMS", new ArrayList<Parcelable>(cartList));
                // Start the BuyBookActivity
                startActivity(intent);
            } else {
                // Show a message or handle the case when the cart is empty
                Toast.makeText(CartActivity.this, "Your cart is empty", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to clear the cart
    private void clearCart() {
        CartManager.getInstance().clearCart(); // Clear the cart in CartManager
        cartAdapter.notifyDataSetChanged();
    }
}
