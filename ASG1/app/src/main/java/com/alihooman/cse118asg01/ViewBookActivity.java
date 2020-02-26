package com.alihooman.cse118asg01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);

        // Array list elements are:
        // Title, Author, Year, Description, ISBN, Ebook
        ArrayList<String> book = getIntent().getStringArrayListExtra("book");

        TextView title = findViewById(R.id.bookTitleTxtViewR);
        TextView author = findViewById(R.id.bookAuthorTxtViewR);
        TextView year = findViewById(R.id.bookYearTxtViewR);
        TextView description = findViewById(R.id.bookDescriptionTxtViewR);
        TextView isbn = findViewById(R.id.bookISBNTxtViewR);
        TextView ebook = findViewById(R.id.bookEbookTxtViewR);

        title.setText(book.get(0));
        author.setText(book.get(1));
        year.setText(book.get(2));
        description.setText(book.get(3));
        isbn.setText(book.get(4));
        ebook.setText(book.get(5));

    }
}
