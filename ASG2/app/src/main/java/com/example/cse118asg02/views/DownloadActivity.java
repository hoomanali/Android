package com.example.cse118asg02.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cse118asg02.R;
import com.example.cse118asg02.imageDB.DBHelper;
import com.example.cse118asg02.images.Utils;
import com.example.cse118asg02.images.MyImage;

public class DownloadActivity extends AppCompatActivity {

    EditText urlInput;
    EditText titleInput;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        urlInput = findViewById(R.id.imgURL);
        titleInput = findViewById(R.id.imgTitle);

        context = DownloadActivity.this;
    }

    /**
     * Checks if the URL is valid and if there is a network connection.
     * If URL is invalid, prompt user with a toast.
     * If Network is down save URL and title to database and prompt with a toast.
     * Image will be downloaded when list of images is viewed.
     * @param view The current view.
     */
    public void onDownloadImageClick(View view) {
        String url = urlInput.getText().toString();
        String title = titleInput.getText().toString();

        Boolean urlIsValid = Utils.UrlValid(url);
        Boolean networkIsUp = Utils.NetworkConnected(context);

        if(urlIsValid && networkIsUp) {
            String toast = "Saved.";
            saveToastAndReturn(context, toast, url, title);
        } else if(!urlIsValid) {
            Toast.makeText(context, "Invalid URL", Toast.LENGTH_LONG).show();
        } else {
            String toast = "No network, saving URL to database to download later.";
            saveToastAndReturn(context, toast, url, title);
        }
    }

    /**
     * Save image to DB, show appropriate toast, and return to parent activity.
     * @param c Current activity context.
     * @param toast The toast message to be displayed.
     * @param url URL of the image being saved.
     * @param title Title of the image being saved.
     */
    public void saveToastAndReturn(Context c, String toast, String url, String title) {
        MyImage myImage = new MyImage(url, title);
        DBHelper dbHelper = new DBHelper(context);
        dbHelper.addImage(myImage);
        Toast.makeText(c, toast, Toast.LENGTH_LONG).show();
        NavUtils.navigateUpFromSameTask(this);
    }
}
