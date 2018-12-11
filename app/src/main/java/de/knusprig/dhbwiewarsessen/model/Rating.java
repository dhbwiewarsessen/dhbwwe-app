package de.knusprig.dhbwiewarsessen.model;

import java.util.Calendar;

public class Rating {

    private Integer rating;
    private String comment;
    private User user;
    private Calendar date;
    private Dish dish;

    public Rating(Integer rating, String comment, User user, Calendar date, Dish dish) {
        this.rating = rating;
        this.comment = comment;
        this.user = user;
        this.date = date;
        this.dish = dish;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}
