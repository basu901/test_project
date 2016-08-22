package com.example.shaunakbasu.finalproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shaunakbasu.finalproject.data.UserDetailsColumns;
import com.example.shaunakbasu.finalproject.data.UserDetailsProvider;
import com.squareup.picasso.Picasso;

/**
 * Created by shaunak basu on 22-08-2016.
 */
public class UserDetails extends AppCompatActivity {
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_details);
        id=getIntent().getStringExtra("id");

        Cursor user_details=getApplicationContext().getContentResolver().query(UserDetailsProvider.User.CONTENT_URI,
                new String[]{UserDetailsColumns.FIRST_NAME,UserDetailsColumns.LAST_NAME,UserDetailsColumns.EMAIL,UserDetailsColumns.PHONE,UserDetailsColumns.PORTRAIT},
                UserDetailsColumns._ID+" =?",
                new String[]{id},null);

        if(user_details.moveToFirst()){
            ((TextView)findViewById(R.id.p_first_name_content)).setText(user_details.getString(user_details.getColumnIndex(UserDetailsColumns.FIRST_NAME)));
            ((TextView)findViewById(R.id.p_last_name_content)).setText(user_details.getString(user_details.getColumnIndex(UserDetailsColumns.LAST_NAME)));
            ((TextView)findViewById(R.id.p_email_content)).setText(user_details.getString(user_details.getColumnIndex(UserDetailsColumns.EMAIL)));
            ((TextView)findViewById(R.id.p_phone_content)).setText(user_details.getString(user_details.getColumnIndex(UserDetailsColumns.PHONE)));
            ImageView imageView=(ImageView)findViewById(R.id.p_image);
            int image=user_details.getInt(user_details.getColumnIndex(UserDetailsColumns.PORTRAIT));
            Picasso.with(getApplicationContext()).load(image).into(imageView);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // do something useful

                Intent intent=new Intent(UserDetails.this,CoursePage.class);
                intent.putExtra("id",id);
                startActivity(intent);
                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }
}
