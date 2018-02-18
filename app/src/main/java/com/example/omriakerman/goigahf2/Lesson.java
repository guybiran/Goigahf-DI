package com.example.omriakerman.goigahf2;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 05/10/2017.
 */

public class Lesson {
    private Integer lessonId;
    private Student student;
    private String startAddress, finishAddress;
    private int lessonNumber;
    private Date startTime, endTime;


    public Lesson(){}

    public Lesson(Integer id, Student stu, String start, String finish, long startT, long endT){
        lessonId = id;
        student = stu;
        startAddress = start;
        finishAddress = finish;
        startTime = new Date(startT);
        endTime = new Date(endT);
        lessonNumber = stu.getNumberOfLessons() + 1;
        stu.increaseNumberOfLessons();
    }
    //return (minutes-9 < 0) ? String.valueOf(hour) + ":" + "0" + String.valueOf(minutes)  : String.valueOf(hour) + ":" + String.valueOf(minutes);
    public Date getStartTimeDate() {return startTime;}
    public Date getEndTimeDate() {return endTime;}

    public long getStartTimeHourInMillis(){return startTime.getTime();}

    public String getHM_StartString(){return getStartHourString() + ":" + getStartMinutesString();}
    public String getHM_EndString(){return getEndHourString() + ":" + getEndMinutesString();}

    public String getStartHourString(){return String.valueOf(startTime.getHours());}
    public String getEndHourString(){return String.valueOf(endTime.getHours());}

    public String getStartMinutesString(){return (startTime.getMinutes() < 10) ? ("0"+String.valueOf(startTime.getMinutes())) : String.valueOf(startTime.getMinutes());}
    public String getEndMinutesString(){return (endTime.getMinutes() < 10) ? ("0"+String.valueOf(endTime.getMinutes())) : String.valueOf(endTime.getMinutes());}


    public int getLessonYear(){
        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        return c.get(Calendar.YEAR);
    }
    public int getLessonMonth(){
        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        return c.get(Calendar.MONTH)+1;
    }
    public int getLessonDay(){
        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        return c.get(Calendar.DATE);
    }
    public String getLessonDateString(){return String.valueOf(getLessonDay()) + "/" + String.valueOf(getLessonMonth()) + "/" + String.valueOf(getLessonYear());}

    public String getStudentName(){return student.getName();}
    public String getStartAddress(){return startAddress;}
    public String getFinishAddress(){return finishAddress;}
    public int getLessonNumber(){return lessonNumber;}
    public Integer getLessonId(){
        return lessonId;
    }

    public Student getStudent(){return student;}
}
