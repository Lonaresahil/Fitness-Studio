package com.example.Fitness_Studio.notes.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.Fitness_Studio.R;
import com.example.Fitness_Studio.notes.adapter.NotesAdapter;
import com.example.Fitness_Studio.notes.database.NotesDatabase;
import com.example.Fitness_Studio.notes.entities.Note;
import com.example.Fitness_Studio.notes.listeners.NotesListener;

import java.util.ArrayList;
import java.util.List;

public class notes1 extends AppCompatActivity implements NotesListener {

    public static final int REQUEST_CODE_ADD_NOTE = 1;
    public static final int REQUEST_CODE_UPDATE_NOTE = 2;
    public static final int REQUEST_CODE_SHOW_NOTES = 3;


    private RecyclerView notesRecyclerview;
    private List<Note> noteList;
    private NotesAdapter notesAdapter;

    private int noteClickedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes1);

        ImageView imageAddNoteMain = findViewById(R.id.imageAddNoteMain);
        imageAddNoteMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), notes_CreateNotes.class),
                        REQUEST_CODE_ADD_NOTE);
            }
        });

        notesRecyclerview = findViewById(R.id.notesRecyclerView);
        notesRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        );

        noteList = new ArrayList<>();
        notesAdapter = new NotesAdapter(noteList,this);
        notesRecyclerview.setAdapter(notesAdapter);


        getNotes(REQUEST_CODE_SHOW_NOTES,false);

        EditText inputSearch = findViewById(R.id.inputSearch);
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                notesAdapter.cancelTimer();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (noteList.size() !=0){
                    notesAdapter.searchNotes(s.toString());
                }
            }
        });
    }

    @Override
    public void onNoteClicked(Note note, int position) {
        noteClickedPosition = position;
        Intent intent = new Intent(getApplicationContext(),notes_CreateNotes.class);
        intent.putExtra("isViewOrUpdated",true);
        intent.putExtra("note",note);
        startActivityForResult(intent, REQUEST_CODE_UPDATE_NOTE);
    }

    private void getNotes(final int requestCode, final boolean isNoteDeleted) {

        @SuppressLint("StaticFieldLeak")
        class GetNotesTask extends AsyncTask<Void, Void, List<Note>> {

            @Override
            protected List<Note> doInBackground(Void... voids) {
                return NotesDatabase.getDatabase(getApplicationContext()).noteDao().getAllNote();
            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                super.onPostExecute(notes);
               if (requestCode == REQUEST_CODE_SHOW_NOTES){
                   noteList.addAll(notes);
                   notesAdapter.notifyDataSetChanged();
               } else if (requestCode == REQUEST_CODE_ADD_NOTE){
                   noteList.add(0, notes.get(0));
                   notesAdapter.notifyItemInserted(0);
                   notesRecyclerview.smoothScrollToPosition(0);
               } else if (requestCode == REQUEST_CODE_UPDATE_NOTE){
                   noteList.remove(noteClickedPosition);
                   noteList.add(noteClickedPosition, notes.get(noteClickedPosition));
                   notesAdapter.notifyItemChanged(noteClickedPosition);

                   if (isNoteDeleted){
                       notesAdapter.notifyItemRemoved(noteClickedPosition);
                   } else {
                       noteList.add(noteClickedPosition, notes.get(noteClickedPosition));
                       notesAdapter.notifyItemChanged(noteClickedPosition);
                   }
               }
            }
        }
        new GetNotesTask().execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_NOTE && resultCode == RESULT_OK){
            getNotes(REQUEST_CODE_ADD_NOTE,false);
        } else if (requestCode == REQUEST_CODE_UPDATE_NOTE && resultCode == RESULT_OK){
            if (data != null){
                getNotes(REQUEST_CODE_UPDATE_NOTE, data.getBooleanExtra("isNoteDeleted",false));
            }
        }
    }
}