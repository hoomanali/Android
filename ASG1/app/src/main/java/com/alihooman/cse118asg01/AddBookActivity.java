package com.alihooman.cse118asg01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.util.Log;

public class AddBookActivity extends AppCompatActivity {

    private static String TAG = "[AddBookActivity]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        // Attach widgets to usable variables
        final EditText titleEditTxt = findViewById(R.id.addBookTitleEditTxt);
        final EditText authorEditTxt = findViewById(R.id.addBookAuthorEditTxt);
        final EditText yearEditTxt = findViewById(R.id.addBookYearEditTxt);
        final EditText descriptionEditTxt = findViewById(R.id.addBookDescriptionEditTxt);
        final EditText isbnEditTxt = findViewById(R.id.addBookISBNEditTxt);
        final Switch e_bookSwitch = findViewById(R.id.addBookSwitch);

        // Initialize save button
        Button saveButton;
        saveButton = findViewById(R.id.addBookSaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(TAG, "Save button pressed.");

                // Grab all the inputs
                // Initialize the year to -1, if it's not over written, then it's not valid.
                int year = -1;
                if(!yearEditTxt.getText().toString().trim().equals("")) {
                    year = Integer.parseInt(yearEditTxt.getText().toString().trim());
                }
                String title = titleEditTxt.getText().toString().trim();
                String author = authorEditTxt.getText().toString().trim();
                String description = descriptionEditTxt.getText().toString().trim();
                String isbn = isbnEditTxt.getText().toString().trim();
                Boolean e_book = e_bookSwitch.isChecked();

                // Build a Book
                Book book = new Book(title, author, year, description, isbn, e_book);

                // Validate input and save or throw toast error and keep trying
                if(book.hasValidData()) {
                    long rowID = saveBook(v, book);
                    if(rowID == -1) {
                        bookError(1);
                    } else {
                        finish();
                    }
                } else {
                    bookError(0);
                }
            }
        });
    }

    /**
     * Adds a new user defined book to local storage.
     *
     * @param v The current view.
     * @param book The book to be added.
     */
     long saveBook(View v, Book book) {
        // Print to log
        Log.d(TAG, "Saving book...\n"
                + "\nTitle: " + book.getTitle()
                + "\nAuthor: " + book.getAuthor()
                + "\nYear: " + book.getYear()
                + "\nDescription: " + book.getDescription()
                + "\nISBN: " + book.getIsbn()
                + "\nE-book: " + book.getE_book());

        // Add book to database
        BookDBHelper dbHelper = new BookDBHelper(v.getContext());
        long rowID = dbHelper.addBook(book, dbHelper);
        if(rowID == -1) {
            Log.d(TAG, "Could not save book.");
        } else {
            Log.d(TAG, "Book added. Row ID: " + rowID);
        }

        return rowID;
    }

    /**
     * Add a log message and toast on invalid book or unnsuccesful database add.
     */
    void bookError(int error) {
        if(error == 0) {
            // Invalid book data
            Log.d(TAG, "Invalid book data");
            String toastMsg = "Please check book information and try again.";
            Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_LONG).show();
        } else if(error == 1) {
            // Could not save to database
            Log.d(TAG, "Could not save to database.");
            String toastMsg = "Could not add book to databse, please try again.";
            Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_LONG).show();
        }

    }
}
