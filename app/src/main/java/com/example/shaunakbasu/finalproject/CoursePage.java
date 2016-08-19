package com.example.shaunakbasu.finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shaunakbasu.finalproject.data.Course;
import com.example.shaunakbasu.finalproject.data.Filler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shaunak basu on 14-08-2016.
 */
public class CoursePage extends AppCompatActivity{

    ArrayList<Course> myCourses=new ArrayList<>();
    ArrayList<Filler> personal_list_fields=new ArrayList<>();
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courses);

        myCourses.add(new Course(R.drawable.english,"English"));
        myCourses.add(new Course(R.drawable.maths,"Basic Mathematics"));
        myCourses.add(new Course(R.drawable.environment,"Environmental Science"));
        myCourses.add(new Course(R.drawable.health,"Health and Hygiene"));

        for(int i=0;i<getResources().getStringArray(R.array.text_fields).length;i++)
            personal_list_fields.add(new Filler(getResources().getStringArray(R.array.text_fields)[i], getResources().getStringArray(R.array.mipmap_fields)[i]));


            GridView gridView = (GridView) findViewById(R.id.course_grid);
            CourseAdapter courseArrayAdapter = new CourseAdapter();
            gridView.setAdapter(courseArrayAdapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    CourseAdapter courseAdapter = (CourseAdapter) adapterView.getAdapter();
                    Course current_course = courseAdapter.getItem(i);
                    String course_name = current_course.getCourse_text();
                    int course_image = current_course.getCourse_image();

                    Intent intent = new Intent(CoursePage.this, PersonalDetailsFragment.class);
                    intent.putExtra("course_name", course_name);
                    intent.putExtra("image_details", course_image);
                    startActivity(intent);
                }
            });

            Log.v(CoursePage.class.getSimpleName(),"After gridview");
            /*mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            mDrawerList = (ListView) findViewById(R.id.personal_list_view);
            ArrayAdapter<Filler> personalPaneAdapter = new PersonalPaneAdapter();
            mDrawerList.setAdapter(personalPaneAdapter);
            // Set the list's click listener
            mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
            */
    }




     private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
        selectItem(position);
        }
     }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {

        Filler item=personal_list_fields.get(position);
        String text=item.getText();
        switch(text){
            case "Courses":onStart();
                break;
        }
        // Create a new fragment and specify the planet to show based on position
        /*PersonalDetailsFragment fragment = new PersonalDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        fragment.setArguments(args);

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer*/

        mDrawerList.setItemChecked(position, true);
        setTitle(text);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    public void setTitle(CharSequence title) {
        //mTitle = title;
        getActionBar().setTitle(title);
    }


    class PersonalPaneAdapter extends ArrayAdapter<Filler> {
        Context context;
        ArrayList<Filler> data;

        public PersonalPaneAdapter() {
            super(getApplicationContext(),R.layout.personal_listview_elements,personal_list_fields);
            this.context=getApplicationContext();
            this.data=personal_list_fields;
        }

        public class ViewHolder{
            TextView text;
            ImageView image;
        }

        public View getView( View convertView,int position, ViewGroup parent) {
            ViewHolder viewHolder=new ViewHolder();
            if (convertView == null) {
                //result = LayoutInflater.from(parent.getContext()).inflate(R.layout.personal_listview_elements, parent, false);
                convertView=getLayoutInflater().inflate(R.layout.personal_listview_elements,parent,false);

                viewHolder.text=(TextView)convertView.findViewById(R.id.list_text);
                viewHolder.image=(ImageView)convertView.findViewById(R.id.list_image);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder)convertView.getTag();
            }

            Filler item = data.get(position);

            viewHolder.text.setText(item.getText());
            viewHolder.image.setImageResource(Integer.parseInt(item.getImage()));

            return convertView;
        }
    }

    class CourseAdapter extends ArrayAdapter<Course>{

        Context context;

        public CourseAdapter(){
            super(getApplicationContext(),R.layout.individual_course,myCourses);
        }

        public class ViewHolder{
            TextView course_text;
            ImageView course_image;
        }

        public View getView(int position, ViewGroup parent,View convertView){

            ViewHolder vh=new ViewHolder();
            int image_id;
            String text;
            if(convertView==null){

                convertView=getLayoutInflater().inflate(R.layout.individual_course,parent,false);

                vh.course_text=(TextView)convertView.findViewById(R.id.course_text_desc);
                vh.course_image=(ImageView)convertView.findViewById(R.id.course_image_view);



                convertView.setTag(vh);
            }

            else{
                vh=(ViewHolder)convertView.getTag();
            }

            image_id=myCourses.get(position).getCourse_image();
            text=myCourses.get(position).getCourse_text();

            vh.course_text.setText(text);
            vh.course_image.setImageResource(image_id);

            return convertView;
        }
    }
}
