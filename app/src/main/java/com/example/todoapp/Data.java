package com.example.todoapp;

public class Data {


    private String text;
    private boolean isChecked;



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Data(String text, boolean isChecked) {
        this.text = text;
        this.isChecked = isChecked;
    }
}
