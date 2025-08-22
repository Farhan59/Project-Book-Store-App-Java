package edu.ewubd.projectbookstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class BookDetailsActivity extends AppCompatActivity {
    private Button backButton, btnAddToCart;
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve the book details from the intent extras
        Intent intent = getIntent();
        String title = intent.getStringExtra("BOOK_TITLE");
        String author = intent.getStringExtra("BOOK_AUTHOR");
        String description = intent.getStringExtra("BOOK_DESCRIPTION");
        double rating = intent.getDoubleExtra("BOOK_RATING", 0);
        double price = intent.getDoubleExtra("BOOK_PRICE", 0);
        String imageUrl = intent.getStringExtra("BOOK_IMAGE_URL");
        String publishDate = intent.getStringExtra("BOOK_PUBLISH_DATE");

        // Create a Book object
        book = new Book(imageUrl, title, author, rating, price, description, publishDate);

        // Find the TextViews and ImageView in your layout
        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvAuthor = findViewById(R.id.tvBookAuthorName);
        TextView tvDescription = findViewById(R.id.tvBookDescription);
        TextView tvRating = findViewById(R.id.tvBookRating);
        TextView tvPrice = findViewById(R.id.tvPrice);
        TextView tvPublishDate = findViewById(R.id.tvBookPublishDate);
        ImageView imageView = findViewById(R.id.imgView);
        backButton = findViewById(R.id.backButton);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        backButton.setOnClickListener(v -> finish());

        // Set the book details to the UI elements
        tvTitle.setText(title);
        tvAuthor.setText("Author: " + author);
        tvDescription.setText(description);
        tvRating.setText("Rating: " + rating);
        tvPrice.setText("Price: ৳" + price);
        tvPublishDate.setText("Published on: " + publishDate);

        // Load the book image using Glide or Picasso
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(imageView);

        // Add to Cart button click listener
        btnAddToCart.setOnClickListener(v -> {
            // Add book to cart
            CartManager.getInstance().addToCart(book);

            // Navigate to CartActivity
            startActivity(new Intent(BookDetailsActivity.this, CartActivity.class));
        });
    }
}

