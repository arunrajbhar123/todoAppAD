package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Data> arrayList = new ArrayList<>();

    EditText todo;
    Button submitBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        todo = findViewById(R.id.editText);
        submitBtn = findViewById(R.id.submitBtn);


        RecyclerView myRecyclerView = findViewById(R.id.recyclerView);

        for (int i = 0; i < 2; i++) {
            boolean condition = (i % 2 == 0);
            String title = condition ? "This is a completed task." : "This is not a completed task.";
            arrayList.add(new Data(title, condition));


        }


        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, arrayList);
        myRecyclerView.setAdapter(adapter);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = todo.getText().toString();
                if (TextUtils.isEmpty(title)) {
                    Toast.makeText(MainActivity.this, "Please enter task.", Toast.LENGTH_SHORT).show();
                } else {
                    arrayList.add(new Data(title, false));
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    adapter.notifyItemInserted(arrayList.size() - 1);
                    todo.setText("");
                    myRecyclerView.smoothScrollToPosition(arrayList.size() - 1);
                }


            }
        });


    }

}