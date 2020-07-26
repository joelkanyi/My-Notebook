package com.kanyideveloper.mvvmnoteapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    //we make it static so that the class can be singleton,
    //means we cannot make multiple instane of the class
    // which means we use the same instance of the class everywhere
    private static NoteDatabase instance;

    //no method body
    //this will be used to access the note dao
    public abstract NoteDao noteDao();

    //synchronized means that a single thread at time can access the class
    public static synchronized NoteDatabase getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return  instance;
    }

    private static RoomDatabase.Callback roomCallBack  = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };


    private static class PopulateDBAsyncTask extends AsyncTask<Void,Void,Void> {
        private NoteDao noteDao;

        private PopulateDBAsyncTask(NoteDatabase db){
            noteDao = db.noteDao()
        }

        protected Void doInBackground(Void... voids) {
            noteDao.insertNote(new Note("Title 1","Description 1", "Priority 1"));
            noteDao.insertNote(new Note("Title 12","Description 12", "Priority 12"));
            noteDao.insertNote(new Note("Title 13","Description 13", "Priority 13"));

            return null;
        }
    }
}
