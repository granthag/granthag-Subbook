package com.test.test;

/**
 * Created by micag on 2018-02-05.
 */

/**
 * Represents a subscription
 */

public class subscription {
    private String name;
    private String date;
    private float price;
    private String note;

    /**
     * Constructs a subscription with name, date, price, and note
     *
     * @param name Name of the subscription
     * @param date Date of the subscription
     * @param price Cost of the subscription
     * @param note Optional note for subscription
     */
    public subscription(String name, String date, float price, String note){
        this.name = name;
        this.date = date;
        this.price = price;
        this.note = note;
    }

    /**
     * Gets name of subscription
     *
     * @return String of subscription name
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name of the subscription
     *
     * @param name string representing the name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Returns the date of the subscription
     *
     * @return String of subscription date
     */
    public String getDate(){
        return date;
    }

    /**
     * Sets the subscription date
     *
     * @param date String representing the date
     */
    public void setDate(String date){
        this.date = date;
    }

    /**
     * gets the price of the subscription
     *
     * @return float representing price
     */
    public float getCost(){
        return price;
    }

    /**
     * Sets the price of the subscription
     *
     * @param price float of price
     */
    public void setCost(float price){
        this.price = price;
    }

    /**
     * Returns the note for the subscription
     *
     * @return String representing the note
     */
    public String getComment(){
        return note;
    }

    /**
     * Sets the note for the subscription
     *
     * @param note String representing note
     */
    public void setComment(String note){
        this.note = note;
    }
}