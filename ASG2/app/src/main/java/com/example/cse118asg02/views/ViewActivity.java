package com.example.cse118asg02.views;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.example.cse118asg02.R;
import com.example.cse118asg02.imageDB.DBHelper;
import com.example.cse118asg02.imageDB.ImgCursorAdapter;

public class ViewActivity extends AppCompatActivity {

    ListView listView;
    DBHelper db;
    public ImgCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        listView = findViewById(R.id.imageListView);
        db = new DBHelper(ViewActivity.this);
        Cursor cursor = db.getCursor();
        adapter = new ImgCursorAdapter(this, cursor);
        listView.setAdapter(adapter);
    }
}
