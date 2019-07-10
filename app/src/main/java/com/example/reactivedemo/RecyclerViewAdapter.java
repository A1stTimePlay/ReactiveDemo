package com.example.reactivedemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<String> data;
    private List<String> datafull;

    public RecyclerViewAdapter() {
        data = new ArrayList<>();
    }

    public RecyclerViewAdapter(List<String> data) {
        this.data = data;
        this.datafull = new ArrayList<>(data);
    }

    public void addItem(String item){
        data.add(item);
        datafull = new ArrayList<>(data);
    }

    public void filterData(String string) {

        data.clear();

        if (string == null || string.length() == 0) data.addAll(datafull);

        else {

            String filterPattern = string.toLowerCase().trim();


            for (String item : datafull) {

                if (item.toLowerCase().contains(filterPattern)) {

                    data.add(item);

                }

            }

        }

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String string = data.get(position);
        holder.textView.setText(string);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

}
