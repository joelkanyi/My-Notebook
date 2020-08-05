package com.kanyideveloper.mvvmnoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_DESCRIPTION = "description";
    public static final String EXTRA_PRIORITY = "priority";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

        edit_title = findViewById(R.id.edit_text_title);
        edit_description = findViewById(R.id.edit_text_description);

        numberPicker_priority = findViewById(R.id.number_picker_priority);

        numberPicker_priority.setMaxValue(10);
        numberPicker_priority.setMinValue(1);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");
    }

    private void saveNote(){
        String title = edit_title.getText().toString();
        String description = edit_description.getText().toString();
        int priority = numberPicker_priority.getValue();


        Intent data = new Intent();

        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_PRIORITY,priority);

        setResult(RESULT_OK,data);
        finish();
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