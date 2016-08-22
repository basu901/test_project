package com.example.shaunakbasu.finalproject;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shaunakbasu.finalproject.adapters.RegisteredCourseAdapter;
import com.example.shaunakbasu.finalproject.data.RegisteredCoursesColumns;
import com.example.shaunakbasu.finalproject.data.RegisteredCoursesProvider;
import com.example.shaunakbasu.finalproject.utils.DataInsert;

/**
 * Created by shaunak basu on 21-08-2016.
 */
public class RegisteredCoursesFragment extends android.support.v4.app.Fragment {

    Cursor registered;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registered = getActivity().getContentResolver().query(RegisteredCoursesProvider.Registered_Courses.CONTENT_URI,
                new String[]{RegisteredCoursesColumns._ID,RegisteredCoursesColumns.C_NAME, RegisteredCoursesColumns.C_IMAGE,RegisteredCoursesColumns.USER_ID},
                RegisteredCoursesColumns.USER_ID+" =?", new String[]{DataInsert.id}, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View rootView;

        if(registered.moveToFirst()){
            rootView=inflater.inflate(R.layout.registered_courses,container,false);
            ListView listView=(ListView)rootView.findViewById(R.id.registered_course_list);
            RegisteredCourseAdapter adapter=new RegisteredCourseAdapter(getActivity(),registered,0);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        String str=((TextView)view.findViewById(R.id.course_text_desc)).getText().toString();
                    switch(str){
                        case "English":Intent intent=new Intent(getActivity(),CourseExpand.class);
                                        startActivity(intent);
                                        break;
                        default:
                            Toast.makeText(getActivity().getApplicationContext(),"Under Construction :(...Check out English Course!",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        else{
            rootView=inflater.inflate(R.layout.no_registered_courses,container,false);
        }

            return rootView;
    }

}
