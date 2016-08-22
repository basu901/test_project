package com.example.shaunakbasu.finalproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shaunakbasu.finalproject.data.UserDetailsColumns;
import com.example.shaunakbasu.finalproject.data.UserDetailsProvider;
import com.example.shaunakbasu.finalproject.utils.DataInsert;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText email=(EditText)findViewById(R.id.email);
        final EditText password=(EditText)findViewById(R.id.password);
        Button sign_in=(Button)findViewById(R.id.sign_in);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(email.getText().toString(),password.getText().toString());

                Cursor user_row=getApplicationContext().getContentResolver().query(UserDetailsProvider.User.CONTENT_URI,
                        new String[]{UserDetailsColumns.EMAIL,UserDetailsColumns.PASSWORD,UserDetailsColumns._ID},
                        UserDetailsColumns.EMAIL+" =?",
                        new String[]{email.getText().toString()},null);
                if(user_row.moveToFirst()){
                    String password_checker=user_row.getString(user_row.getColumnIndex(UserDetailsColumns.PASSWORD));
                    String id=user_row.getString(user_row.getColumnIndex(UserDetailsColumns._ID));
                    if(password_checker.equals(password.getText().toString())){
                        DataInsert.setID(id);
                        Intent intent=new Intent(MainActivity.this,CoursePage.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Sorry wrong credentials!",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(getApplicationContext(),"Your not registered...So,SIGN UP!!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button sign_up=(Button)findViewById(R.id.sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed(){
        this.finishAffinity();
    }

}
