package com.example.shaunakbasu.finalproject;


import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shaunakbasu.finalproject.data.EnglishColumns;
import com.example.shaunakbasu.finalproject.data.EnglishProvider;
import com.example.shaunakbasu.finalproject.data.RegisteredCoursesColumns;
import com.example.shaunakbasu.finalproject.data.RegisteredCoursesProvider;
import com.example.shaunakbasu.finalproject.utils.DataInsert;
import com.squareup.picasso.Picasso;

/**
 * Created by shaunak basu on 17-08-2016.
 */
public class CourseInfoFragment extends Fragment {
    String course_name,course_desc,course_duration;
    int course_image;
    Bundle args;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        args=this.getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View rootView=inflater.inflate(R.layout.course_info_fragment,container,false);

        course_name=args.getString("course_name");
        course_desc=args.getString("course_desc");
        course_duration=args.getString("course_duration");
        course_image=args.getInt("course_image");

        ImageView imageView=(ImageView)rootView.findViewById(R.id.c_image);
        Picasso.with(getActivity()).load(course_image).into(imageView);
        ((TextView)rootView.findViewById(R.id.c_display_content)).setText(course_name);
        ((TextView)rootView.findViewById(R.id.c_desc_content)).setText(course_desc);
        ((TextView)rootView.findViewById(R.id.c_duration_content)).setText(course_duration);

        Button register=(Button)rootView.findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                registerCourse(course_name);
            }
        });

        return rootView;

    }


    public void registerCourse(String c_name){
        Uri newRow;
        long confirm=-1;
        Cursor course=getActivity().getApplicationContext().getContentResolver().query(RegisteredCoursesProvider.Registered_Courses.CONTENT_URI,
                new String[]{RegisteredCoursesColumns._ID,RegisteredCoursesColumns.USER_ID},
                RegisteredCoursesColumns.C_NAME+" =?"+" AND "+RegisteredCoursesColumns.USER_ID+" =?",
                new String[]{c_name, DataInsert.id},null);

        if(course.moveToFirst()){
            Toast.makeText(getActivity().getApplicationContext(),"You are already enrolled!",Toast.LENGTH_SHORT).show();
        }
        else{
            ContentValues course_values=new ContentValues();
            course_values.put(RegisteredCoursesColumns.C_NAME,course_name);
            course_values.put(RegisteredCoursesColumns.C_DESC,course_desc);
            course_values.put(RegisteredCoursesColumns.C_DUR,course_duration);
            course_values.put(RegisteredCoursesColumns.C_IMAGE,course_image);
            course_values.put(RegisteredCoursesColumns.USER_ID,DataInsert.id);

            newRow=getActivity().getApplicationContext().getContentResolver().insert(RegisteredCoursesProvider.Registered_Courses.CONTENT_URI,course_values);
            confirm=ContentUris.parseId(newRow);


            if(course_name.equals("English")){
                ContentValues english_values=new ContentValues();
                english_values.put(EnglishColumns.TP_NAME,"Basics in English Speaking");
                english_values.put(EnglishColumns.TP_SRC,"tU3lRm5W3oQ");
                english_values.put(EnglishColumns.PARENT,"Speaking in English");

                newRow=getActivity().getApplicationContext().getContentResolver().insert(EnglishProvider.English.CONTENT_URI,english_values);

                english_values.put(EnglishColumns.TP_NAME,"English Fillers");
                english_values.put(EnglishColumns.TP_SRC,"tKmkB7OVO_M");
                english_values.put(EnglishColumns.PARENT,"Speaking in English");

                newRow=getActivity().getApplicationContext().getContentResolver().insert(EnglishProvider.English.CONTENT_URI,english_values);

                english_values.put(EnglishColumns.TP_NAME,"Using Should,Would,Could");
                english_values.put(EnglishColumns.TP_SRC,"O-wmulY5Z4o");
                english_values.put(EnglishColumns.PARENT,"Prepositions");

                newRow=getActivity().getApplicationContext().getContentResolver().insert(EnglishProvider.English.CONTENT_URI,english_values);

                english_values.put(EnglishColumns.TP_NAME,"Prepositions of place - in, on, at");
                english_values.put(EnglishColumns.TP_SRC,"_zhw3BUysUA");
                english_values.put(EnglishColumns.PARENT,"Prepositions");

                newRow=getActivity().getApplicationContext().getContentResolver().insert(EnglishProvider.English.CONTENT_URI,english_values);
            }
        }

        if(confirm!=-1){
            Toast.makeText(getActivity().getApplicationContext(),"Enrolled!",Toast.LENGTH_SHORT).show();
        }
    }
}
