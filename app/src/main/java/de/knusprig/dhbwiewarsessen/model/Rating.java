package de.knusprig.dhbwiewarsessen.model;

import java.util.Calendar;

public class Rating {

    private int id;
    private int rating;
    private String comment;
    private int user_id;
    private Calendar date;
    private String dish;

    public Rating(Calendar date, String dish, int rating, String comment, int user_id) {
        this.rating = rating;
        this.comment = comment;
        this.user_id = user_id;
        this.date = date;
        this.dish = dish;
    }

    public Rating(int id, Calendar date, String dish, int rating, String comment, int user_id) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.user_id = user_id;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }
}
