package de.knusprig.dhbwiewarsessen.model;

import java.util.Observable;

public class User extends Observable {
    String username;
    String email;
    String name;
    String password;

    public User(String username, String email, String name, String password) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        setChanged();
        notifyObservers();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        setChanged();
        notifyObservers();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        setChanged();
        notifyObservers();
    }
}
