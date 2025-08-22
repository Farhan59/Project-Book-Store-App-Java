package edu.ewubd.projectbookstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private List<Book> bookList;
    private DatabaseReference databaseReference;
    private TextView tvCategoryTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        tvCategoryTitle = findViewById(R.id.tvTitle);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookList = new ArrayList<>();
        bookAdapter = new BookAdapter(bookList, book -> {
            Intent intent = new Intent(BookListActivity.this, BookDetailsActivity.class);
            intent.putExtra("BOOK_TITLE", book.getTitle());
            intent.putExtra("BOOK_AUTHOR", book.getAuthor());
            intent.putExtra("BOOK_DESCRIPTION", book.getDescription());
            intent.putExtra("BOOK_RATING", book.getRating());
            intent.putExtra("BOOK_PRICE", book.getPrice());
            intent.putExtra("BOOK_PUBLISH_DATE", book.getPublishdate());
            intent.putExtra("BOOK_IMAGE_URL", book.getImageUrl());
            startActivity(intent);
        });

        recyclerView.setAdapter(bookAdapter);

        Intent intent = getIntent();
        String category = intent.getStringExtra("CATEGORY_TITLE");

        if (category == null) {
            Toast.makeText(this, "Category not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        tvCategoryTitle.setText(category);
        databaseReference = FirebaseDatabase.getInstance().getReference("books").child(category);
        fetchBooksFromFirebase();

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        Button btnGoToCart = findViewById(R.id.btnGoToCart);
        btnGoToCart.setOnClickListener(v -> {
            startActivity(new Intent(this, CartActivity.class));
            finish();
        });
    }

    private void fetchBooksFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    bookList.clear();
                    for (DataSnapshot bookSnapshot : dataSnapshot.getChildren()) {
                        Book book = bookSnapshot.getValue(Book.class);
                        if (book != null) {
                            bookList.add(book);
                        } else {
                            Log.e("BookListActivity", "Book is null");
                        }
                    }
                    bookAdapter.notifyDataSetChanged();
                } else {
                    Log.e("BookListActivity", "DataSnapshot does not exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BookListActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                Log.e("BookListActivity", "Database error: " + databaseError.getMessage());
            }
        });
    }

}
