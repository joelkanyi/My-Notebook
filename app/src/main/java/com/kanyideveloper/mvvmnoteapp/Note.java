package com.kanyideveloper.mvvmnoteapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

@PrimaryKey(autoGenerate = true)
private int id;

private String title;

private String description;

private String priority;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public int getId(){
        return id;
    }

    public Note(String title, String description, String priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }


    public void setId(int id) {
        this.id = id;
    }
}
