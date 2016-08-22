package com.example.shaunakbasu.finalproject.data;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by shaunak basu on 16-08-2016.
 */
public class Course {
    int course_image;
    String course_text;
    String description;
    String duration;

    public Course(int course_image,String course_text,String description,String duration){
        this.course_image=course_image;
        this.course_text=course_text;
        this.description=description;
        this.duration=duration;
    }

    public int getCourse_image() {
        return course_image;
    }

    public String getCourse_text() {
        return course_text;
    }

    public String getDescription() {
        return description;
    }

    public String getDuration() {
        return duration;
    }

    public void setCourse_image(int course_image) {
        this.course_image = course_image;
    }

    public void setCourse_text(String course_text) {
        this.course_text = course_text;
    }
}
