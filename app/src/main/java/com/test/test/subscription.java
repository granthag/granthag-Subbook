package com.test.test;

/**
 * Created by micag on 2018-02-05.
 */



public class subscription {
    private String name;
    private String date;
    private float price;
    private String note;


    public subscription(String name, String date, float price, String note){
        this.name = name;
        this.date = date;
        this.price = price;
        this.note = note;
    }


    public String getName(){
        return name;
    }


    public void setName(String name){
        this.name = name;
    }

    public String getDate(){
        return date;
    }


    public void setDate(String date){
        this.date = date;
    }


    public float getCost(){
        return price;
    }


    public void setCost(float price){
        this.price = price;
    }


    public String getComment(){
        return note;
    }


    public void setComment(String note){
        this.note = note;
    }
}