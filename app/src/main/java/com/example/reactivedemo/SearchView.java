package com.example.reactivedemo;

import java.util.ArrayList;
import java.util.List;

public class SearchView implements android.widget.SearchView.OnQueryTextListener, Observable {

    private List<Observer> observerList = new ArrayList<>();

    public SearchView(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        nortifyChange(newText);
        return false;
    }

    @Override
    public void nortifyChange(String string) {
        for (Observer observer:observerList) observer.onChange(string);
    }

    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }
}
