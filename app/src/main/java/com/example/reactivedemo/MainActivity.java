package com.example.reactivedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    RecyclerViewAdapter adapter = new RecyclerViewAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        List<PackageInfo> apps = getPackageManager().getInstalledPackages(0);
//        List<String> data = new ArrayList<>();
//        for(int i=0;i<apps.size();i++) {
//            String appname = apps.get(i).applicationInfo.loadLabel(getPackageManager()).toString();
//            data.add(appname);
//        }
//
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
