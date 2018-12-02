package de.knusprig.dhbwiewarsessen.model;

import java.util.List;
import java.util.Observable;

public class Menu extends Observable {
    private List<Dish> dishes;

    public Menu(List<Dish> dishes) {
        this.dishes = dishes;
        setChanged();
        notifyObservers();
    }

    public List<Dish> getDishes() {
        return dishes;
    }
}
