package com.example.project3;


import java.io.Serializable;

public class MyObject implements Serializable {
    private String name;
    private int age;
    private String Group;
    private int Grade;

    public MyObject(String name,int age,String Group,int Grade){
        this.name = name;
        this.age = age;
        this.Group = Group;
        this.Grade = Grade;

    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }
    public String  getGroup(){
        return Group;
    }
    public int getGrade(){
        return Grade;
    }
}