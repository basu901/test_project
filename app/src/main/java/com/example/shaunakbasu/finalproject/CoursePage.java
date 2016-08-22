package com.example.shaunakbasu.finalproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shaunakbasu.finalproject.data.*;
import com.example.shaunakbasu.finalproject.utils.DataInsert;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by shaunak basu on 14-08-2016.
 */
public class CoursePage extends AppCompatActivity {


    ArrayList<Filler> personal_list_fields = new ArrayList<>();
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courses);
        final String id= DataInsert.id;
;

        //for(int i=0;i<getResources().getStringArray(R.array.text_fields).length;i++)
        //  personal_list_fields.add(new Filler(getResources().getStringArray(R.array.text_fields)[i], getResources().getIntArray(R.array.mipmap_fields)[i]));

        Log.v("Checking ID:",id);
        Cursor personal=getApplicationContext().getContentResolver().query(UserDetailsProvider.User.CONTENT_URI,
                new String[]{UserDetailsColumns.LAST_NAME,UserDetailsColumns.FIRST_NAME,UserDetailsColumns.IMAGE},
                UserDetailsColumns._ID+" =?",
                new String[]{id},null);

        if(personal.moveToFirst()){
            String first_name=personal.getString(personal.getColumnIndex(UserDetailsColumns.FIRST_NAME));
            String last_name=personal.getString(personal.getColumnIndex(UserDetailsColumns.LAST_NAME));
            String name=first_name+" "+last_name;
            int image=personal.getInt(personal.getColumnIndex(UserDetailsColumns.IMAGE));
            ((TextView)findViewById(R.id.personal_name_text)).setText(name);
            ImageView imageView=(ImageView)findViewById(R.id.personal_image_view);
            Picasso.with(getApplicationContext()).load(image).into(imageView);

        }

        personal_list_fields.add(new Filler("Courses", R.mipmap.courses));
        personal_list_fields.add(new Filler("Questions", R.mipmap.questions));
        personal_list_fields.add(new Filler("User Details",R.mipmap.user_details));
        personal_list_fields.add(new Filler("Help & Feedback", R.mipmap.help));
        personal_list_fields.add(new Filler("Logout", R.mipmap.logout));


        RegisteredCoursesFragment fragment = new RegisteredCoursesFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.registered_details_container, fragment).commit();

        Log.v(CoursePage.class.getSimpleName(), "After gridview");
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.personal_list_view);
        ArrayAdapter<Filler> personalPaneAdapter = new PersonalPaneAdapter();
        mDrawerList.setAdapter(personalPaneAdapter);
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Filler item = personal_list_fields.get(position);
                String text = item.getText();
                Log.v("The text is:", text);
                switch (text) {
                    case "Courses":
                        Intent intent = new Intent(CoursePage.this, CourseInfoMain.class);
                        intent.putExtra("id",id);
                        startActivity(intent);
                        break;

                    case "User Details":
                            Intent user_details=new Intent(CoursePage.this,UserDetails.class);
                            user_details.putExtra("id",id);
                            startActivity(user_details);
                            break;
                    case "Logout":
                        Intent intent_main_page = new Intent(CoursePage.this, MainActivity.class);
                        startActivity(intent_main_page);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Sorry site under construction :(", Toast.LENGTH_SHORT).show();

                }

            }
        });

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

        public View getView( int position,View convertView, ViewGroup parent) {
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
            viewHolder.image.setImageResource(item.getImage());

            return convertView;
        }
    }

}
