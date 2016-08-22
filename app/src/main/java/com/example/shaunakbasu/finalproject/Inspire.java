package com.example.shaunakbasu.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shaunakbasu.finalproject.utils.DataInsert;
import com.squareup.picasso.Picasso;


/**
 * Created by shaunak basu on 22-08-2016.
 */
public class Inspire extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.inspire);

        DataInsert.loadUsers(getApplicationContext());

        TextView textView = (TextView) findViewById(R.id.i_text);
        ImageView imageView = (ImageView) findViewById(R.id.i_image);
        textView.setText(getString(R.string.story));
        Picasso.with(getApplicationContext()).load(R.drawable.inspire_pic).into(imageView);

        Button button = (Button) findViewById(R.id.i_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Inspire.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
