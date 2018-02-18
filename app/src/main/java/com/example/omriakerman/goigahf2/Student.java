package com.example.omriakerman.goigahf2;

/**
 * Created by user on 05/10/2017.
 */

public class Student {
    private String studentName;
    private Instructor myInstructor;
    private String usualPickUpLocation;
    private int numberOfLessons;
    private String idNumber;
    private String phoneNumber;
    private Vehicle vehicle;

    public Student(){}

    public Student(Instructor myInst, String name, String id, String phoneNumber, int numberOfLessons, Vehicle vehic){
        setMyInstructor(myInst);
        setName(name);
        setIdNumber(id);
        setPhoneNumber(phoneNumber);
        setNumberOfLessons(numberOfLessons);
        setVehicle(vehic);
    }

    public void setMyInstructor(Instructor inst){myInstructor = inst;}
    public Instructor getMyInstructor(){return myInstructor;}

    public void setName(String n){studentName = n;}
    public String getName(){return studentName;}

    public void setUsualPickUpLocation(String s){usualPickUpLocation = s;}
    public String getUsualPickUpLocation(){return usualPickUpLocation;}

    public void setNumberOfLessons(int n){numberOfLessons = n;}
    public void increaseNumberOfLessons(){++numberOfLessons;}
    public int getNumberOfLessons(){return numberOfLessons;}

    public void setIdNumber(String id){idNumber = id;}
    public String getIdNumber(){return idNumber;}

    public void setPhoneNumber(String n){phoneNumber = n;}
    public String getPhoneNumber(){return phoneNumber;}

    public void setVehicle(Vehicle c){vehicle = c;}
    public Vehicle getVehicle(){return vehicle;}
}
