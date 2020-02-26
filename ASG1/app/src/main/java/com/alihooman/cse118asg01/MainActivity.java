package com.alihooman.cse118asg01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "[MainActivity]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add Book button
        Button addBookButton;
        addBookButton = findViewById(R.id.buttonMainAdd);
        addBookButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(TAG, "Add Book button pressed.");
                addBook(v);
            }
        });

        // View Catalogue button
        Button viewCatalogueButton;
        viewCatalogueButton = findViewById(R.id.buttonMainViewCat);
        viewCatalogueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(TAG, "View Catalogue button pressed.");
                viewCatalogue(v);
            }
        });

        // Exit button
        Button exitButton;
        exitButton = findViewById(R.id.buttonMainExit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(TAG, "Exit button pressed.");
                exitApp();
            }
        });

    }

    /**
     * Creates a new intent, adds any extras, and starts the Add Book activity.
     *
     * @param view The current activity's view
     */
    public void addBook(View view) {
        Intent intent = new Intent(this, AddBookActivity.class);
        startActivity(intent);
    }

    /**
     * Creates a new intent, adds any extra, and starts the View Catalogue activity.
     *
     * @param view The current activity's view
     */
    public void viewCatalogue(View view) {
        Intent intent = new Intent(this, CatalogueActivity.class);
        startActivity(intent);
    }

    /**
     * Attempts to gracefully save data and exit application.
     */
    void exitApp() {
        Log.d(TAG, "exitApp() called.");
        finish();
    }
}
