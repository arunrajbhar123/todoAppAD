package com.example.todoapp;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private final Context context;
    private final ArrayList<Data> data;

    public RecyclerViewAdapter(Context context, ArrayList<Data> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.items, parent, false);

        return new RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        final Data currentItem = data.get(position);
        holder.title.setText(currentItem.getText());
        holder.isChecked.setChecked(currentItem.isChecked());
        boolean isChecked = currentItem.isChecked();
        if (isChecked) {
            holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {

            holder.title.setPaintFlags(holder.title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }


        holder.isChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                currentItem.setChecked(b);
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        CheckBox isChecked;

        ImageButton deleteBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView);
            isChecked = itemView.findViewById(R.id.checkBox);
            deleteBtn = itemView.findViewById(R.id.imageButton);


            isChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    int position = getAdapterPosition();


                }
            });


            deleteBtn.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 int position = getAdapterPosition();
                                                 if (position != RecyclerView.NO_POSITION) {
                                                     String title = data.get(position).getText();
                                                     handleDeleteItems(data, position, title);
                                                 }
                                             }
                                         }

            );

        }
    }


    public void handleDeleteItems(ArrayList<Data> data, int position, String title) {
        Toast.makeText(context, "Items is Delete " + title, Toast.LENGTH_SHORT).show();
        data.remove(position);
        notifyDataSetChanged();

    }
}
