package com.example.project_10.database;

public class Car {
    private int id;
    private String mark,YearOfRelease,color,price;


    public Car(int id, String mark, String YearOfRelease, String color,String price) {
        this.id = id;
        this.mark= mark;
        this.YearOfRelease = YearOfRelease;
        this.color = color;
        this.price = price;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMark() {
        return mark;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getYearOfRelease() {
        return YearOfRelease;
    }
    public void setYearOfRelease(String YearOfRelease) {
        this.YearOfRelease = YearOfRelease;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
}

