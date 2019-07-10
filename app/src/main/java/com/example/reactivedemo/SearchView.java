package com.example.reactivedemo;

import java.util.ArrayList;
import java.util.List;

public class SearchView implements android.widget.SearchView.OnQueryTextListener {

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
