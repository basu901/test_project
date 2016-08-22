package com.example.shaunakbasu.finalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by shaunak basu on 21-08-2016.
 */
public class CourseInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_info_activity);
        if(savedInstanceState==null){
            Bundle args=getIntent().getExtras();
            CourseInfoFragment courseInfoFragment =new CourseInfoFragment();
            courseInfoFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().add(R.id.course_details_container, courseInfoFragment).commit();
        }
    }
}
