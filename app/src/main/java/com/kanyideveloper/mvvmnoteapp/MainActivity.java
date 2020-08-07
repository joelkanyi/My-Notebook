package com.kanyideveloper.mvvmnoteapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    NoteViewModel model;
    public static final int ADD_NOTE_RESULT = 1;
    private FloatingActionButton add_note_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_note_fab = findViewById(R.id.add_new_note);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        add_note_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewNoteActivity.class);
                startActivityForResult(intent,ADD_NOTE_RESULT);
            }
        });
        final NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        //final NoteViewModel model = new ViewModelProvider(this).get(NoteViewModel.class);
        model = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(NoteViewModel.class);
        model.allNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                //update our recyclerview
                adapter.setNotes(notes);
                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });

        new ItemTouchHelper(new SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                model.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Note Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_RESULT && resultCode == RESULT_OK){
         String title = data.getStringExtra(AddNewNoteActivity.EXTRA_TITLE);
         String description = data.getStringExtra(AddNewNoteActivity.EXTRA_DESCRIPTION);
         int priority = data.getIntExtra(AddNewNoteActivity.EXTRA_PRIORITY,1);


         Note note = new Note(title,description,priority);
        model.insert(note);

            Toast.makeText(this, "New Note Saved", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Note not Saved", Toast.LENGTH_SHORT).show();
        }
    }
}