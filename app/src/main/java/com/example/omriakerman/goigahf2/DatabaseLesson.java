package com.example.omriakerman.goigahf2;

/**
 * Created by guyb on 02/03/18.
 */

public class DatabaseLesson {
    // TODO - make private and create getters and setters
//    private String lessonId;
//    private Student student;
//    private String startAddress, finishAddress;
//    private int lessonNumber;
//    private long startTime, endTime;
    public String lessonId;
    public Student student;
    public String startAddress, finishAddress;
    public int lessonNumber;
    public long startTime;
    public long endTime;

    public DatabaseLesson() {}

    public DatabaseLesson(String id, Student stu, String start, String finish, long startT, long endT){
        lessonId = id;
        student = stu;
        startAddress = start;
        finishAddress = finish;
        startTime = startT;
        endTime = endT;
        lessonNumber = stu.getNumberOfLessons() + 1;
        stu.increaseNumberOfLessons();
    }
}
