package com.example.reactivedemo;

public interface Observable {
    public void nortifyChange(String string);
    public void addObserver(Observer observer);
    public void removeObserver(Observer observer);
}
