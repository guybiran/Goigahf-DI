package com.example.omriakerman.goigahf2;

/**
 * Created by lg199 on 11/6/2017.
 */

public class Vehicle {
    private int id;
    private String name;
    private String license;

    public Vehicle(){}

    public Vehicle(int i, String n, String l){
        id = i;
        name = n;
        license = l;
    }

    public int getId(){return id;}

    public String getName() {return name;}

    public String getLicense() {return license;}
}
