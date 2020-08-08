package com.kanyideveloper.mvvmnoteapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String note;

    public String getNote(){ return note; }

    public int getId(){
        return id;
    }

    public Note(String note) {
        this.note = note;
    }

    public void setId(int id) {
        this.id = id;
    }
}
