package com.kanyideveloper.mynotebook;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String note;

    private String day;

    public String getDay(){
        return day;
    }

    public String getNote(){ return note; }

    public int getId(){
        return id;
    }

    public Note(String note,String day) {
        this.note = note;
        this.day = day;
    }

    public void setId(int id) {
        this.id = id;
    }
}
