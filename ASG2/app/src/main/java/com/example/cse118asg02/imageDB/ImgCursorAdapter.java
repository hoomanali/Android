package com.example.cse118asg02.imageDB;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cse118asg02.R;
import com.example.cse118asg02.images.DownloadImage;
import com.example.cse118asg02.images.MyImage;

public class ImgCursorAdapter extends CursorAdapter {

    public ImgCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    /**
     * Inflate the list_item view and return it.
     * @param context The current activity's context.
     * @param cursor The cursor for the image database.
     * @param parent The view's parent.
     * @return The inflated list_item view.
     */
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    /**
     * Creates the view and populates its widgets width information from cursor.
     * @param view The current view.
     * @param context The current activity's context.
     * @param cursor The cursor for the image database.
     */
    public void bindView(View view, final Context context, Cursor cursor) {
        String url = cursor.getString(cursor.getColumnIndex(DBContract.DBEntry.COLUMN_URL));
        String title = cursor.getString(cursor.getColumnIndex(DBContract.DBEntry.COLUMN_TITLE));
        MyImage image = new MyImage(url, title);

        TextView index = view.findViewById(R.id.listItemID);
        ImageView imageView = view.findViewById(R.id.listItemImage);
        TextView imgTitle = view.findViewById(R.id.listItemTitle);
        ImageView delete = view.findViewById(R.id.listItemDelete);

        final String rowID = cursor.getString(cursor.getColumnIndex(DBContract.DBEntry._ID));

        Bitmap bitmap = null;
        try {
            bitmap = new DownloadImage().execute(url).get();
            imageView.setImageBitmap(bitmap);
        } catch(Exception e) {
            Log.d("[List Item]", "Create image failed.");
        }

        index.setText(rowID);
        imgTitle.setText(title);
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DBHelper dbhelper = new DBHelper(context);
                dbhelper.deleteImage(rowID);
                String t = "Image deleted. Hit back to refresh. I didn't correctly implement adapter.notifyDataSetChanged()";
                Toast.makeText(context, t, Toast.LENGTH_LONG).show();
            }
        });
    }
}
