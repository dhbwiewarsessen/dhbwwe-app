package de.knusprig.dhbwiewarsessen.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Rating {

    private int id;
    private int rating;
    private String comment;
    private String username;
    private Calendar date;
    private String dish;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy | HH:mm");

    public Rating(Calendar date, String dish, int rating, String comment, String username) {
        this.rating = rating;
        this.comment = comment;
        this.date = date;
        this.dish = dish;
        this.username = username;
    }

    public Rating(int id, Calendar date, String dish, int rating, String comment, String username) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.username = username;
        this.date = date;
        this.dish = dish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Calendar getDate() {
        return date;
    }

    public String getStringDate(){
        return simpleDateFormat.format(date.getTime());
    }

    public String getDish() {
        return dish;
    }

}
