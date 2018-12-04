package de.knusprig.dhbwiewarsessen.model;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Menu extends Observable implements Observer {
    private List<Dish> dishes;

    public Menu(List<Dish> dishes) {
        this.dishes = dishes;
        for (Dish dish : dishes){
            dish.addObserver(Menu.this);
        }
        setChanged();
        notifyObservers();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    @Override
    public void update(Observable o, Object arg) {
        //forward the information that the dishes have been changed
        setChanged();
        notifyObservers();
    }
}
