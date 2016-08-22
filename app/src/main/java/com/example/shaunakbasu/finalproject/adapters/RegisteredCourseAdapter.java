package com.example.shaunakbasu.finalproject.adapters;

import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shaunakbasu.finalproject.R;
import com.example.shaunakbasu.finalproject.data.RegisteredCoursesColumns;
import com.squareup.picasso.Picasso;

/**
 * Created by shaunak basu on 21-08-2016.
 */
public class RegisteredCourseAdapter extends CursorAdapter{
    public RegisteredCourseAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.registered_course_elements, parent, false);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView course_name_view = (TextView) view.findViewById(R.id.course_text_desc);
        ImageView course_image_view = (ImageView) view.findViewById(R.id.course_image_view);
        // Extract properties from cursor
        String course_name = cursor.getString(cursor.getColumnIndex(RegisteredCoursesColumns.C_NAME));
        int image = cursor.getInt(cursor.getColumnIndex(RegisteredCoursesColumns.C_IMAGE));
        // Populate fields with extracted properties
        course_name_view.setText(course_name);
        Picasso.with(context).load(image).into(course_image_view);
    }
}