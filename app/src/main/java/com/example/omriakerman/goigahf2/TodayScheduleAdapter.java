package com.example.omriakerman.goigahf2;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lg199 on 10/22/2017.
 */

public class TodayScheduleAdapter extends ArrayAdapter {
    private ArrayList<Lesson> myObjects;
    private ArrayList<Integer> objectsLayoutTypes = new ArrayList<Integer>();
    int i;
    private Lesson lesson, nextLesson;
    private int vehicleId, nextVehicleId;
    int lessonLayoutId = R.layout.today_schedule_list_item;     // original: today_schedule_list_item
    int switchCarsLessonLayoutId = R.layout.today_schedule_switch_cars_list_item;
    int emptyLayoutId = R.layout.empty_item;
    int SWITCH = 1, LESSON = 0, EMPTY = -1;


    public TodayScheduleAdapter(Context context, int textViewResourceId, ArrayList<Lesson> objects){
        super(context, textViewResourceId, objects);
        this.myObjects = objects;
        //myObjects.add(new Lesson());
    }

    @Override
    public int getViewTypeCount(){
        return 3;
    }

    @Override
    public int getCount(){
        return myObjects.size();
    }

    @Override
    public int getItemViewType(int position){
        if(position == myObjects.size()-2){
            return LESSON;
        }else if(position == myObjects.size()-1){
            return EMPTY;
        } else {
            lesson = myObjects.get(position);
            nextLesson = myObjects.get(position+1);

            vehicleId = lesson.getStudent().getVehicle().getId();
            nextVehicleId = nextLesson.getStudent().getVehicle().getId();

            if(vehicleId != nextVehicleId){
                return SWITCH;
            } else {
                return LESSON;
            }
        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Date currentTime = Calendar.getInstance().getTime();
        int viewType, layoutId=emptyLayoutId;
        Lesson currLesson;

        viewType = getItemViewType(position);
        if(viewType == LESSON) {layoutId = lessonLayoutId;}
        else if(viewType == SWITCH){layoutId = switchCarsLessonLayoutId;}

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }

        currLesson = myObjects.get(position);

        if(currLesson != null && viewType != EMPTY){
            TextView hours = (TextView) convertView.findViewById(R.id.hour);
            TextView minutes = (TextView) convertView.findViewById(R.id.minutes);
            TextView name = (TextView) convertView.findViewById(R.id.name_textView);
            TextView address = (TextView) convertView.findViewById(R.id.start_address);
            TextView number = (TextView) convertView.findViewById(R.id.lesson_number);
            TextView vehicle = (TextView) convertView.findViewById(R.id.vehicle);

            ImageView status_symbol = (ImageView) convertView.findViewById(R.id.status_symbol);
            RelativeLayout currentLessonRect = (RelativeLayout) convertView.findViewById(R.id.current_lesson_rect);

            String hoursText = currLesson.getStartHourString();
            String minutesText = currLesson.getStartMinutesString();
            String nameText = currLesson.getStudentName();
            String addressText = "איסוף: " + currLesson.getStartAddress();
            String numberText = String.valueOf(currLesson.getLessonNumber());
            String vehicleText = "רכב: " + currLesson.getStudent().getVehicle().getName();

            if(status_symbol != null){
                if (currentTime.after(currLesson.getEndTimeDate())) {
                    status_symbol.setImageResource(R.drawable.icon_done);
                    status_symbol.setVisibility(View.VISIBLE);
                    currentLessonRect.setVisibility(View.INVISIBLE);
                } else if(currentTime.after(currLesson.getStartTimeDate()) && currentTime.before(currLesson.getEndTimeDate())){
                    status_symbol.setImageResource(R.drawable.icon_in_progress);
                    status_symbol.setVisibility(View.VISIBLE);
                    currentLessonRect.setVisibility(View.VISIBLE);
                } else {
                    status_symbol.setVisibility(View.INVISIBLE);
                    currentLessonRect.setVisibility(View.INVISIBLE);

                }
            }

            if(hours != null) {
                hours.setText(hoursText);
            }
            if(minutes != null){
                minutes.setPaintFlags(minutes.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                minutes.setText(minutesText);
            }
            if(name != null) {name.setText("  " + nameText);}
            if(address != null) {address.setText("  " + addressText);}
            if(number != null){number.setText(numberText);}
            if(vehicle != null) {vehicle.setText(vehicleText);}
        }

        return convertView;
    }

}
