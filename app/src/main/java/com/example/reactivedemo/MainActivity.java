package com.example.reactivedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.List;

import rx.Observable;
import rx.Observer;

public class MainActivity extends AppCompatActivity {

    RecyclerViewAdapter adapter = new RecyclerViewAdapter();
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        editText= (EditText) findViewById(R.id.editText);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        refreshTheList();
        filteredTheList();
    }

    private Observable<String> searchName(){
        return Observable.create(subscriber -> {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    subscriber.onNext(editable.toString());
                }
            });
        });
    }

    private void filteredTheList(){
        searchName().subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                adapter.filterData(s);
            }
        });
    }

    private Observable<String> getName () {
        return Observable.create(subscriber -> {
            List<PackageInfo> apps = getPackageManager().getInstalledPackages(0);
            for (PackageInfo app : apps) {
                subscriber.onNext(app.applicationInfo.loadLabel(getPackageManager()).toString());
            }
            subscriber.onCompleted();

        });
    }

    private void refreshTheList(){
        getName().subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                adapter.addItem(s);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
