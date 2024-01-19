package com.example.Fitness_Studio.notes.listeners;

import com.example.Fitness_Studio.notes.entities.Note;

public interface NotesListener {
    void onNoteClicked(Note note, int position);
}
