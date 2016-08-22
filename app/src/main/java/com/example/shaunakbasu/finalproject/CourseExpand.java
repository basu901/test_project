package com.example.shaunakbasu.finalproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.shaunakbasu.finalproject.adapters.ExpandableListAdapter;
import com.example.shaunakbasu.finalproject.data.EnglishColumns;
import com.example.shaunakbasu.finalproject.data.EnglishProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by shaunak basu on 22-08-2016.
 */
public class CourseExpand extends AppCompatActivity {

    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ExpandableListAdapter listAdapter;
    HashMap<String,String> videoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_details_expand);
        expListView=(ExpandableListView)findViewById(R.id.list_expand);
        prepareData();
        listAdapter=new ExpandableListAdapter(this,listDataHeader,listDataChild);
        expListView.setAdapter(listAdapter);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                String ec=listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                String url=videoUrl.get(ec);
                Intent intent=new Intent(CourseExpand.this,CourseVideo.class);
                intent.putExtra("video_url",url);
                intent.putExtra("title",ec);
                startActivity(intent);
                return false;
            }
        });

    }

    private void prepareData(){

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        videoUrl=new HashMap<>();

        listDataHeader.add("Speaking in English");
        listDataHeader.add("Prepositions");

        Cursor c_english=getApplicationContext().getContentResolver().query(EnglishProvider.English.CONTENT_URI,
                new String[]{EnglishColumns.TP_NAME,EnglishColumns.TP_SRC}, EnglishColumns.PARENT+" =?",new String[]{"Speaking in English"},null);

        if(c_english.moveToFirst()){

            List<String> S_E=new ArrayList<>();
            do{
                String ec=c_english.getString(c_english.getColumnIndex(EnglishColumns.TP_NAME));
                S_E.add(ec);
                String vid=c_english.getString(c_english.getColumnIndex(EnglishColumns.TP_SRC));
                videoUrl.put(ec,vid);
            }while(c_english.moveToNext());

            listDataChild.put(listDataHeader.get(0),S_E);
        }

        c_english=getApplicationContext().getContentResolver().query(EnglishProvider.English.CONTENT_URI,
                new String[]{EnglishColumns.TP_NAME,EnglishColumns.TP_SRC}, EnglishColumns.PARENT+" =?",new String[]{"Prepositions"},null);

        if(c_english.moveToFirst()){

            List<String> P=new ArrayList<>();
            do{
                String ec=c_english.getString(c_english.getColumnIndex(EnglishColumns.TP_NAME));
                P.add(ec);
                String vid=c_english.getString(c_english.getColumnIndex(EnglishColumns.TP_SRC));
                videoUrl.put(ec,vid);
            }while(c_english.moveToNext());

            listDataChild.put(listDataHeader.get(1),P);
        }

    }

}
