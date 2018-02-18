package com.example.omriakerman.goigahf2;


//import android.icu.util.GregorianCalendar;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by user on 09/11/2016.
 */

public class Database {
    public static ArrayList<Lesson> allLessons = new ArrayList<>();
    public static Instructor instructor;
    public static ArrayList<Student> allStudents = new ArrayList<>();

    Set<Integer> generatedIds = new LinkedHashSet<Integer>();
    Random rng = new Random();

    Calendar cal = Calendar.getInstance();

    public Database(){

        int mHour = cal.get(Calendar.HOUR);
        int mMinute = cal.get(Calendar.MINUTE);
        int mYear = cal.get(Calendar.YEAR);
        int mMonth = cal.get(Calendar.MONTH);
        int mDay = cal.get(Calendar.DAY_OF_WEEK);

        setFakeInstructor();
        setFakeStudents();
        setFakeLessons();
        allLessons = sortLessonsByHour(allLessons);
    }

    public void sortByHourMinutes(){

    }

    public static Lesson getLessonById(Integer id){
        Lesson t = new Lesson();
        for(int i=0; i<allLessons.size(); ++i){
            if(getLesson(i).getLessonId().equals(id)){
                t = getLesson(i);
                break;
            }
        }
        return t;
    }


    public static Lesson getLesson(int index){return allLessons.get(index);}

    public static ArrayList<Lesson> getAllLessonsInDate(int y, int m, int d){
        ArrayList<Lesson> lessons = new ArrayList<>();
        Lesson lesson;

        for(int i=0; i < allLessons.size(); ++i){
            lesson = allLessons.get(i);

            if(lesson.getLessonYear() == y
                    && lesson.getLessonMonth() == m
                    && lesson.getLessonDay() == d){
                lessons.add(lesson);
            }
        }

        return lessons;
    }

    static public ArrayList<Lesson> getAllLessons(){
        return allLessons;
    }

    private Integer generateId(){
        Integer next = rng.nextInt(1000) + 1;

        if(generatedIds.contains(next)){
            generateId();
        }
        return next;
    }

    static public ArrayList<Student> getAllStudents() {return allStudents;}
    
    public Instructor getInstructor(){
        return instructor;
    }

    static public Student getStudentById(String id){
        Student stu = new Student();
        for(int i=0; i<allStudents.size(); ++i){
            stu = allStudents.get(i);
            if(stu.getIdNumber().equals(id)){
                break;
            }
        }
        return stu;
    }


    void setStudentsFromServer(){}

    //Return true if lesson succesfully added, false otherwise.
    boolean addLesson(Lesson l){
        int i;
        Date newDate, date;

        newDate = l.getStartTimeDate();

        for(i=0;i<allLessons.size(); i++){
            date = allLessons.get(i).getStartTimeDate();
            if(date.equals(newDate)) {
                return false;
            }
        }

        allLessons.add(l);
        return true;
    }

    //Set a few lessons for UI check
    public void setFakeLessons(){
        allLessons.clear();
        Calendar cal = Calendar.getInstance();
        int y,m,d,h,min;
        long start, end;
        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH);
        d = cal.get(Calendar.DATE);
        h = cal.get(Calendar.HOUR_OF_DAY);
        min = cal.get(Calendar.MINUTE);

        cal.set(y,m,d,8,0);
        start = cal.getTimeInMillis();
        cal.set(y,m,d,8,40);
        end = cal.getTimeInMillis();
        addLesson(new Lesson(generateId(), getStudentById("318735149"), "מרכזית", "מרכזית", start, end));

        cal.set(y,m,d,7,20);
        start = cal.getTimeInMillis();
        cal.set(y,m,d,8,0);
        end = cal.getTimeInMillis();
        addLesson(new Lesson(generateId(), getStudentById("376498775"), "גשר", "ביג", start, end));

        cal.set(y,m,d,11,20);
        start = cal.getTimeInMillis();
        cal.set(y,m,d,12,0);
        end = cal.getTimeInMillis();
        addLesson(new Lesson(generateId(), getStudentById("318735149"), "מרכזית", "ביג", start, end));

        cal.set(y,m,d,9,20);
        start = cal.getTimeInMillis();
        cal.set(y,m,d,10,0);
        end = cal.getTimeInMillis();
        addLesson(new Lesson(generateId(), getStudentById("304697852"), "טלאל", "גשר", start, end));

        cal.set(y,m,d,10,0);
        start = cal.getTimeInMillis();
        cal.set(y,m,d,10,40);
        end = cal.getTimeInMillis();
        addLesson(new Lesson(generateId(), getStudentById("318735149"), "גשר", "משגב", start, end));

        cal.set(y,m,d,10,40);
        start = cal.getTimeInMillis();
        cal.set(y,m,d,11,20);
        end = cal.getTimeInMillis();
        addLesson(new Lesson(generateId(), getStudentById("304697852"), "מרכזית", "ביג", start, end));

        cal.set(y,m,d,12,40);
        start = cal.getTimeInMillis();
        cal.set(y,m,d,13,20);
        end = cal.getTimeInMillis();
        addLesson(new Lesson(generateId(), getStudentById("022899371"), "ביג", "ביג", start, end));

        cal.set(y,m,d,13,20);
        start = cal.getTimeInMillis();
        cal.set(y,m,d,14,0);
        end = cal.getTimeInMillis();
        addLesson(new Lesson(generateId(), getStudentById("318735149"), "מרכזית", "מרכזית", start, end));

        cal.set(y,m,d,12,0);
        start = cal.getTimeInMillis();
        cal.set(y,m,d,12,40);
        end = cal.getTimeInMillis();
        addLesson(new Lesson(generateId(), getStudentById("304697852"), "מרכזית", "ביג", start, end));

        cal.set(y,m,d,15,25);
        start = cal.getTimeInMillis();
        cal.set(y,m,d,16,15);
        end = cal.getTimeInMillis();
        addLesson(new Lesson(generateId(), getStudentById("304697852"), "בית ספר כרמים", "ביג", start, end));

        cal.set(y,m,d,16,30);
        start = cal.getTimeInMillis();
        cal.set(y,m,d,17,10);
        end = cal.getTimeInMillis();
        addLesson(new Lesson(generateId(), getStudentById("318735149"), "מרכזית", "מרכזית", start, end));

//        cal.set(y,m,d,h,min);
//        start = cal.getTimeInMillis();
//        cal.set(y,m,d,h,min+1);
//        end = cal.getTimeInMillis();
//        addLesson(new Lesson(generateId(), getStudentById("318735149"), "מרכזית", "מרכזית", start, end));
    }

    //Create an instructor for UI check
    public void setFakeInstructor(){
        instructor = new Instructor("דוד אלבז", "659428051", "0524656289");
        instructor = getInstructor();
        instructor.addVehicle(new Vehicle(1, "סיטרואן ידני", "B"));
        instructor.addVehicle(new Vehicle(2, "סקודה אוטומטי", "B"));
    }

    //Add a few students for UI check
    public void setFakeStudents(){
        allStudents.add(new Student(instructor, "עמרי אקרמן", "318735149", "0524026989", 14, instructor.getVehicleById(1)));
        instructor.increaseNumberOfStudentsByOne();

        allStudents.add(new Student(instructor, "גל הלפרין", "376498775", "0524987666", 6, instructor.getVehicleById(2)));
        instructor.increaseNumberOfStudentsByOne();

        allStudents.add(new Student(instructor, "Donald Trump", "326449489", "0546181533", 0, instructor.getVehicleById(1)));
        instructor.increaseNumberOfStudentsByOne();

        allStudents.add(new Student(instructor, "Omri Ackerman", "324644089", "0546781533", 11, instructor.getVehicleById(1)));
        instructor.increaseNumberOfStudentsByOne();

        allStudents.add(new Student(instructor, "גיא בירן", "304697852", "0503697445", 36, instructor.getVehicleById(1)));
        instructor.increaseNumberOfStudentsByOne();

        allStudents.add(new Student(instructor, "דונלד טראמפ", "022899371", "0524322444", 27, instructor.getVehicleById(2)));
        instructor.increaseNumberOfStudentsByOne();

        allStudents.add(new Student(instructor, "Gal Halperin", "024340069", "0546781533", 27, instructor.getVehicleById(2)));
        instructor.increaseNumberOfStudentsByOne();

        allStudents.add(new Student(instructor, "Guy Biran", "251670091", "0546781533", 2, instructor.getVehicleById(1)));
        instructor.increaseNumberOfStudentsByOne();

    }

    public ArrayList<Lesson> sortLessonsByHour(ArrayList<Lesson> lessons){
        int i,j, min, len = lessons.size();
        int minHour, minMin, h, m;
        ArrayList<Lesson> sorted = new ArrayList<>();

        Calendar cal = Calendar.getInstance();

        for(i=0; i<len; i++){
            cal.setTime(lessons.get(0).getStartTimeDate());
            minHour = cal.get(Calendar.HOUR_OF_DAY);
            minMin = cal.get(Calendar.MINUTE);
            min=0;

            for(j=0; j<lessons.size(); j++){
                cal.setTime(lessons.get(j).getStartTimeDate());
                h = cal.get(Calendar.HOUR_OF_DAY);
                m = cal.get(Calendar.MINUTE);

                if(h < minHour){
                    minHour = h;
                    minMin = m;
                    min = j;
                } else if(h == minHour){
                    if(m < minMin){
                        minHour = h;
                        minMin = m;
                        min = j;
                    }
                }
            }
            sorted.add(i, lessons.remove(min));
        }

        return sorted;
    }

}

