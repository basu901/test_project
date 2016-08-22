package com.example.shaunakbasu.finalproject.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.shaunakbasu.finalproject.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by shaunak basu on 20-08-2016.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    List<String> course_topic;
    HashMap<String,List<String>> topic_content;
    Context mContext;

    public ExpandableListAdapter(Context context,List<String> course_topic,HashMap<String,List<String>> topic_content){
        this.course_topic=course_topic;
        this.topic_content=topic_content;
        mContext=context;
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.course_content_child, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.content_child);

        txtListChild.setText(childText);

        return convertView;

    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.course_content_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.content_group);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return course_topic.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return topic_content.get(course_topic.get(groupPosition)).get(childPosition);
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return topic_content.get(course_topic.get(groupPosition))
                .size();
    }

    @Override
    public int getGroupCount() {
        return course_topic.size();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
