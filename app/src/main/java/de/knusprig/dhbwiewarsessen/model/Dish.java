package de.knusprig.dhbwiewarsessen.model;

import java.util.Observable;

public class Dish extends Observable {
    private String title;
    private float price;

    public Dish(String title, float price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
        setChanged();
        notifyObservers();
    }

    public void setPrice(float price) {
        this.price = price;
        setChanged();
        notifyObservers();
    }
}
