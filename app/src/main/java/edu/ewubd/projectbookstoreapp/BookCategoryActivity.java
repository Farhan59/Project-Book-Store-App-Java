package edu.ewubd.projectbookstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BookCategoryActivity extends AppCompatActivity {

    private Button[] categoryButtons;
    private Button btnLogout, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_category);

        btnLogout = findViewById(R.id.btnLogout);
        btnExit = findViewById(R.id.btnExit);
        categoryButtons = new Button[]{
                findViewById(R.id.button1),
                findViewById(R.id.button2),
                findViewById(R.id.button3),
                findViewById(R.id.button4),
                findViewById(R.id.button5),
                findViewById(R.id.button6),
                findViewById(R.id.button7),
                findViewById(R.id.button8),
                findViewById(R.id.button9),
                findViewById(R.id.button10),
                findViewById(R.id.button11),
                findViewById(R.id.button12)
        };

        String[] categories = {
                "Academic", "Fiction", "Philosophy", "Novel",
                "Mystery", "History", "Biography", "Travel",
                "Science Fiction", "Horror", "Adventure", "Short Story"
        };

        for (int i = 0; i < categoryButtons.length; i++) {
            final String category = categories[i];
            categoryButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openBookListActivity(category);
                }
            });
        }

        btnLogout.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        btnExit.setOnClickListener(v -> {
            finish();
        });
    }

    private void openBookListActivity(String category) {
        Intent intent = new Intent(this, BookListActivity.class);
        intent.putExtra("CATEGORY_TITLE", category);
        startActivity(intent);
    }
}
