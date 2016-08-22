package com.example.shaunakbasu.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.shaunakbasu.finalproject.adapters.CourseAdapter;
import com.example.shaunakbasu.finalproject.data.Course;

import java.util.ArrayList;

/**
 * Created by shaunak basu on 21-08-2016.
 */
public class CourseInfoMain extends AppCompatActivity {

    ArrayList<Course> myCourses=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.available_courses);

        myCourses.add(new Course(R.drawable.english,"English","Learn basic english grammar","6 months"));
        myCourses.add(new Course(R.drawable.maths,"Basic Mathematics","Learn basic mathematics and perform calculations","6 months"));
        myCourses.add(new Course(R.drawable.environment,"Environmental Science","Learn how to keep your environment safe","6 months"));
        myCourses.add(new Course(R.drawable.health,"Health and Hygiene","basic health and hygienic practices","2 weeks"));

        GridView gridView = (GridView) findViewById(R.id.course_grid);
            CourseAdapter courseArrayAdapter = new CourseAdapter(getApplicationContext(),R.layout.available_courses,myCourses);
            gridView.setAdapter(courseArrayAdapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    CourseAdapter courseAdapter = (CourseAdapter) adapterView.getAdapter();
                    Course current_course = courseAdapter.getItem(i);

                    Intent intent = new Intent(CourseInfoMain.this,CourseInfoActivity.class);
                    Bundle course_extras=new Bundle();
                    course_extras.putString("course_name",current_course.getCourse_text());
                    course_extras.putString("course_desc",current_course.getDescription());
                    course_extras.putString("course_duration",current_course.getDuration());
                    course_extras.putInt("course_image",current_course.getCourse_image());
                    intent.putExtras(course_extras);
                    startActivity(intent);
                }
            });


    }

}
