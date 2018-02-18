package com.example.omriakerman.goigahf2;

import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by user on 05/10/2017.
 */

public class Instructor {
    private String instructorName;
    private String idNumber;
    private int numberOfStudents=0;
    private String phoneNumber;
    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    public Instructor(){}

    public Instructor(String name, String id, String phoneNumber){
        setInstructorName(name);
        setIdNumber(id);
        setPhoneNumber(phoneNumber);
    }

    public void setInstructorName(String n){instructorName = n;}
    public String getInstructorName(){return instructorName;}

    public void setIdNumber(String id){idNumber = id;}
    public String getIdNumber(){return idNumber;}

    public void setNumberOfStudents(int n){numberOfStudents = n;}
    public void increaseNumberOfStudentsByOne(){++numberOfStudents;}
    public int getNumberOfStudents(){return numberOfStudents;}

    public void setPhoneNumber(String n){phoneNumber = n;}
    public String getPhoneNumber(){return phoneNumber;}

    public int addVehicle(Vehicle v){
        if(vehicles.contains(v)){
            return 0;
        }
        vehicles.add(v);
        return 1;
    }

    public Vehicle getVehicleById(long id){
        Vehicle vec = new Vehicle();
        for(int i=0; i<vehicles.size(); ++i){
            if(id == (vehicles.get(i)).getId()){
                vec = vehicles.get(i);
            }
        }
        return vec;
    }
}
