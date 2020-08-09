package com.kanyideveloper.mynotebook;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 5)
public abstract class NoteDatabase extends RoomDatabase {

    /** We make it static so that the class can be singleton,
    means we cannot make multiple instance of the class
    which means we use the same instance of the class everywhere
     **/
    private static NoteDatabase instance;

    /** this method has no method body
    this will be used to access the note_item DAO **/
    public abstract NoteDao noteDao();

    /** Synchronized means that a single thread at time can access the class **/
    public static synchronized NoteDatabase getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return  instance;
    }
}
