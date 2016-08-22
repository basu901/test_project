package com.example.shaunakbasu.finalproject;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shaunakbasu.finalproject.data.UserDetailsColumns;
import com.example.shaunakbasu.finalproject.data.UserDetailsProvider;
import com.example.shaunakbasu.finalproject.utils.DataInsert;

/**
 * Created by shaunak basu on 22-08-2016.
 */
public class SignUp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try{
                    String first_name=((EditText)findViewById(R.id.editText_first_name)).getText().toString();
                    String last_name=((EditText)findViewById(R.id.editText_last_name)).getText().toString();
                    String email=((EditText)findViewById(R.id.editText_email)).getText().toString();
                    String phone=((EditText)findViewById(R.id.editText_phone)).getText().toString();
                    String password=((EditText)findViewById(R.id.editText_first_name)).getText().toString();

                    int number=Integer.parseInt(phone);


                    ContentValues values=new ContentValues();
                    values.put(UserDetailsColumns.FIRST_NAME,first_name);
                    values.put(UserDetailsColumns.LAST_NAME,last_name);
                    values.put(UserDetailsColumns.EMAIL,email);
                    values.put(UserDetailsColumns.PHONE,number);
                    values.put(UserDetailsColumns.PASSWORD,password);
                    values.put(UserDetailsColumns.IMAGE,R.mipmap.user);
                    values.put(UserDetailsColumns.PORTRAIT,R.drawable.user);

                    Uri newRow=getApplicationContext().getContentResolver().insert(UserDetailsProvider.User.CONTENT_URI,values);

                    if(ContentUris.parseId(newRow)!=-1)
                        Toast.makeText(getApplicationContext(),"Registered!",Toast.LENGTH_SHORT).show();
                    DataInsert.setID(Long.toString(ContentUris.parseId(newRow)));
                    Intent intent=new Intent(SignUp.this,CoursePage.class);
                    intent.putExtra("id",ContentUris.parseId(newRow));
                    startActivity(intent);

                }catch(Exception e){
                    Toast.makeText(getApplicationContext(),"Phone must be a number",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
