package com.example.omriakerman.goigahf2;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;

import static android.content.ContentValues.TAG;

/**
 * Created by user on 05/10/2017.
 */

public class Lesson extends DatabaseLesson {
    private Date startTimeDate, endTimeDate;

    public Lesson(){}

    public Lesson(String id, Student stu, String start, String finish, long startT, long endT){
        super(id, stu, start, finish, startT, endT);
        startTimeDate = new Date(startT);
        endTimeDate = new Date(endT);
        System.out.println("lalalalalalala");
        System.out.println(startTimeDate);
    }

    //return (minutes-9 < 0) ? String.valueOf(hour) + ":" + "0" + String.valueOf(minutes)  : String.valueOf(hour) + ":" + String.valueOf(minutes);
    public Date getStartTimeDate() {return startTimeDate;}
    public Date getEndTimeDate() {return endTimeDate;}

    public long getStartTimeHourInMillis(){return startTimeDate.getTime();}

    public String getHM_StartString(){return getStartHourString() + ":" + getStartMinutesString();}
    public String getHM_EndString(){return getEndHourString() + ":" + getEndMinutesString();}

    public String getStartHourString(){
        System.out.println(startTimeDate);
        String s = String.valueOf(startTimeDate.getHours());
        Log.d(TAG, s);
        return String.valueOf(startTimeDate.getHours());
    } //TODO
    public String getEndHourString(){return String.valueOf(endTimeDate.getHours());}

    public String getStartMinutesString(){return (startTimeDate.getMinutes() < 10) ? ("0"+String.valueOf(startTimeDate.getMinutes())) : String.valueOf(startTimeDate.getMinutes());}
    public String getEndMinutesString(){return (endTimeDate.getMinutes() < 10) ? ("0"+String.valueOf(endTimeDate.getMinutes())) : String.valueOf(endTimeDate.getMinutes());}


    public int getLessonYear(){
        Calendar c = Calendar.getInstance();
        c.setTime(startTimeDate);
        return c.get(Calendar.YEAR);
    }
    public int getLessonMonth(){
        Calendar c = Calendar.getInstance();
        c.setTime(startTimeDate);
        return c.get(Calendar.MONTH)+1;
    }
    public int getLessonDay(){
        Calendar c = Calendar.getInstance();
        c.setTime(startTimeDate);
        return c.get(Calendar.DATE);
    }
    public String getLessonDateString(){return String.valueOf(getLessonDay()) + "/" + String.valueOf(getLessonMonth()) + "/" + String.valueOf(getLessonYear());}

    public String getStudentName(){return student.getName();}
    public String getStartAddress(){return startAddress;}
    public String getFinishAddress(){return finishAddress;}
    public int getLessonNumber(){return lessonNumber;}
    public String getLessonId(){
        return lessonId;
    }

    public Student getStudent(){return student;}
}
