package com.kanyideveloper.mvvmnoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;

public class AddNewNoteActivity extends AppCompatActivity {
    private EditText edit_title;
    private EditText edit_description;
    private NumberPicker numberPicker_priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

        edit_title = findViewById(R.id.edit_text_title);
        edit_description = findViewById(R.id.edit_text_description);

        numberPicker_priority = findViewById(R.id.number_picker_priority);
    }

    private void saveNote(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.save_note:
                saveNote();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}