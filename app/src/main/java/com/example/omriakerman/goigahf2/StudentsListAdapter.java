package com.example.omriakerman.goigahf2;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.annotation.Target;
import java.util.ArrayList;

/**
 * Created by lg199 on 11/17/2017.
 */

public class StudentsListAdapter extends ArrayAdapter {
    private ArrayList<Student> students;
    int studentLayoutId = R.layout.student_list_item;

    public StudentsListAdapter(Context context, int textViewResourceId, ArrayList<Student> objects){
        super(context, textViewResourceId, objects);
        students = objects;
    }

//    @Override
//    public int getViewTypeCount(){
//        return 1;
//    }
//
//    @Override
//    public int getCount(){
//        return students.size();
//    }
//
//    @Override
//    public int getItemViewType(int position) { return 1; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Student currStudent = students.get(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(studentLayoutId, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.name_textView);
        TextView license = (TextView) convertView.findViewById(R.id.license_textView);
        TextView nextLessonDate = (TextView) convertView.findViewById(R.id.date_textView);
        TextView numOfLessons = (TextView) convertView.findViewById(R.id.numOfLessons_textView);

        //ImageView profile = (ImageView) convertView.findViewById(R.id.profile_imageView);

        //profile.setBackground(Drawable.createFromPath("@drawable/icon_profile"));

        name.setText(currStudent.getName());
        license.setText("• רשיון: " + currStudent.getVehicle().getLicense());
        nextLessonDate.setText("10:00-22/11");
        if(numOfLessons != null) {numOfLessons.setText("• מס' שיעורים: " + String.valueOf(currStudent.getNumberOfLessons()));}
        else{Log.e("CONTROL3", "FUCK!");}

        return convertView;
    }


}
