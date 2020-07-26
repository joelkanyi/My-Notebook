package com.kanyideveloper.mvvmnoteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private Context context;
    private List<Note> myNotes = new ArrayList<>();


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note currentNote = myNotes.get(position);
        holder.noteTitle.setText(currentNote.getTitle());
        holder.noteDescription.setText(currentNote.getDescription());
        holder.notePriority.setText(String.valueOf(currentNote.getPriority()));
    }

    @Override
    public int getItemCount() {
        return myNotes.size();
    }

    public void setNotes(List<Note> notes){
        this.myNotes = notes;
        notifyDataSetChanged();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{

        private TextView noteTitle;
        private TextView noteDescription;
        private TextView notePriority;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.findViewById(R.id.description);
            itemView.findViewById(R.id.priority);
            itemView.findViewById(R.id.title);
        }
    }
}
