package com.example.shaunakbasu.finalproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shaunakbasu.finalproject.R;
import com.example.shaunakbasu.finalproject.data.Course;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by shaunak basu on 21-08-2016.
 */
public  class CourseAdapter extends ArrayAdapter<Course> {

    Context context;
    ArrayList<Course> myCourses;

    public CourseAdapter(Context mContext,int resource_id, ArrayList<Course> myCourses){
        super(mContext,resource_id,myCourses);
        this.context=mContext;
        this.myCourses=myCourses;
    }

    public class ViewHolder{
        TextView course_text;
        ImageView course_image;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        ViewHolder vh=new ViewHolder();
        int image_id;
        String text;
        if(convertView==null){

            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =inflater.inflate(R.layout.individual_course,parent,false);

            vh.course_text=(TextView)convertView.findViewById(R.id.course_text_desc);
            vh.course_image=(ImageView)convertView.findViewById(R.id.course_image_view);



            convertView.setTag(vh);
        }

        else{
            vh=(ViewHolder)convertView.getTag();
            vh.course_image.setImageBitmap(null);
        }

        image_id=myCourses.get(position).getCourse_image();
        text=myCourses.get(position).getCourse_text();

        vh.course_text.setText(text);
        Picasso.with(context).load(image_id).into(vh.course_image);

        return convertView;
    }
}