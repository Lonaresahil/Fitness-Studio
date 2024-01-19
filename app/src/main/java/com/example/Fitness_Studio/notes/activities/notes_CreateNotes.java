package com.example.Fitness_Studio.notes.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.Fitness_Studio.R;
import com.example.Fitness_Studio.notes.database.NotesDatabase;
import com.example.Fitness_Studio.notes.entities.Note;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class notes_CreateNotes extends AppCompatActivity {

    private EditText inputNoteTitle, inputNoteSubtitle, inputNoteText;
    private TextView textDateTime;
    private String selectedNoteColor;
    private String selectedImagePath;
    private  ImageView imageNote;


    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;

    private Note alreadyavailableNote;
    private AlertDialog dialogDeleteNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_create_notes);

        ImageView imageBack = findViewById(R.id.imageback);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        inputNoteTitle = findViewById(R.id.inputNoteTitle);
        inputNoteSubtitle = findViewById(R.id.inputNoteSubtitle);
        inputNoteText = findViewById(R.id.inputNote);
        textDateTime = findViewById(R.id.textDateTime);
        imageNote = findViewById(R.id.imageNotes);

        textDateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault())
                .format(new Date())
        );

        ImageView imageSave = findViewById(R.id.imagesave);
        imageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

        selectedNoteColor = "#FFCE3C";
        selectedImagePath = "";

        if (getIntent().getBooleanExtra("isViewOrUpdated", false)){
            alreadyavailableNote = (Note) getIntent().getSerializableExtra("note");
            setViewOrUpdateNote();
        }

        initMiscellaneous();


    }

    private void setViewOrUpdateNote(){
    inputNoteTitle.setText(alreadyavailableNote.getTitle());
    inputNoteSubtitle.setText(alreadyavailableNote.getSubtitle());
    inputNoteText.setText(alreadyavailableNote.getNoteText());
    textDateTime.setText(alreadyavailableNote.getDateTime());

    if (alreadyavailableNote.getImagePath()!= null && !alreadyavailableNote.getImagePath().trim().isEmpty()){
        imageNote.setImageBitmap(BitmapFactory.decodeFile(alreadyavailableNote.getImagePath()));
        imageNote.setVisibility(View.VISIBLE);
        selectedImagePath = alreadyavailableNote.getImagePath();
    }


    }

    private void saveNote() {
        if (inputNoteTitle.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Note title can't be empty!", Toast.LENGTH_SHORT).show();
            return;
        } else if(inputNoteSubtitle.getText().toString().trim().isEmpty()
                && inputNoteText.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Note can't be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        final Note note = new Note();
        note.setTitle(inputNoteTitle.getText().toString());
        note.setSubtitle(inputNoteSubtitle.getText().toString());
        note.setNoteText(inputNoteText.getText().toString());
        note.setDateTime(textDateTime.getText().toString());
        note.setColor(selectedNoteColor);
        note.setImagePath(selectedImagePath);


        @SuppressLint("StaticFieldLeak")
        class SaveNoteTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                NotesDatabase.getDatabase(getApplicationContext()).noteDao().insertNote(note);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        }

        new SaveNoteTask().execute();

    }

    private void initMiscellaneous() {
        final LinearLayout layoutMiscellaneous = findViewById(R.id.layoutMiscellaneous);
        final BottomSheetBehavior<LinearLayout> bottomSheetBehavior = BottomSheetBehavior.from(layoutMiscellaneous);
        layoutMiscellaneous.findViewById(R.id.textMiscellaneous).setOnClickListener(v -> {
            if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        layoutMiscellaneous.findViewById(R.id.layoutImageNote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(notes_CreateNotes.this, new  String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_STORAGE_PERMISSION);
                 } else {
                    selectImage();
                }
            }
        });



//        if(alreadyavailableNote != null){
//            layoutMiscellaneous.findViewById(R.id.layoutDeleteNote).setVisibility(View.VISIBLE);
//            layoutMiscellaneous.findViewById(R.id.layoutDeleteNote).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
//        }
//
//        private void showDeleteNoteDialog(){
//            if(dialogDeleteNote == null){
//                AlertDialog.Builder builder = new AlertDialog.Builder(notes_CreateNotes.this);
//                View view = LayoutInflater.from(this).inflate(R.layout.layout_delete,(ViewGroup) findViewById(R.id.layoutDeleteNoteContainer));
//
//            }
//        }

//        final ImageView imageColor1 = layoutMiscellaneous.findViewById(R.id.imageColor1);
//        final ImageView imageColor2 = layoutMiscellaneous.findViewById(R.id.imageColor2);
//        final ImageView imageColor3 = layoutMiscellaneous.findViewById(R.id.imageColor3);
//        final ImageView imageColor4 = layoutMiscellaneous.findViewById(R.id.imageColor4);

//        layoutMiscellaneous.findViewById(R.id.viewColor1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedNoteColor = "#FFCE3C";
//                imageColor1.setImageResource(R.drawable.ic_baseline_check_circle_outline_24);
//                imageColor2.setImageResource(0);
//                imageColor3.setImageResource(0);
//                imageColor4.setImageResource(0);
//            }
//        });
//
//        layoutMiscellaneous.findViewById(R.id.viewColor2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedNoteColor = "#6DFF4C";
//                imageColor1.setImageResource(0);
//                imageColor2.setImageResource(R.drawable.ic_baseline_check_circle_outline_24);
//                imageColor3.setImageResource(0);
//                imageColor4.setImageResource(0);
//            }
//        });
//
//        layoutMiscellaneous.findViewById(R.id.viewColor3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedNoteColor = "#46FFCA";
//                imageColor1.setImageResource(0);
//                imageColor2.setImageResource(0);
//                imageColor3.setImageResource(R.drawable.ic_baseline_check_circle_outline_24);
//                imageColor4.setImageResource(0);
//            }
//        });
//
//        layoutMiscellaneous.findViewById(R.id.viewColor4).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                selectedNoteColor = "#FFCE3C";
//                imageColor1.setImageResource(0);
//                imageColor2.setImageResource(0);
//                imageColor3.setImageResource(0);
//                imageColor4.setImageResource(R.drawable.ic_baseline_check_circle_outline_24);
//            }
//        });


        if (alreadyavailableNote != null) {
            layoutMiscellaneous.findViewById(R.id.layoutDeleteNote).setVisibility(View.VISIBLE);
            layoutMiscellaneous.findViewById(R.id.layoutDeleteNote).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    showDeleteNoteDialog();
                }
            });
        }
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(getPackageManager())!= null){
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION && grantResults.length>0){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                selectImage();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK){
           if (data != null) {
               Uri selectedImageuri = data.getData();
               if (selectedImageuri != null){
                   try {
                       InputStream inputStream = getContentResolver().openInputStream((selectedImageuri));
                       Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                       imageNote.setImageBitmap(bitmap);
                       imageNote.setVisibility(View.VISIBLE);

                       selectedImagePath = getPathFromUri(selectedImageuri);
                   }catch (Exception exception){
                       Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                   }
               }
           }
        }
    }
    private String getPathFromUri(Uri contentUri){
        String filePath;
        Cursor cursor = getContentResolver()
                .query(contentUri, null,null,null,null);
        if (cursor == null){
            filePath = contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex("_data");
            filePath = cursor.getString(index);
            cursor.close();
        }
        return  filePath;
    }

    private void showDeleteNoteDialog(){
        if(dialogDeleteNote == null){
            AlertDialog.Builder builder = new AlertDialog.Builder(notes_CreateNotes.this);
            View view = LayoutInflater.from(this).inflate(R.layout.layout_delete,(ViewGroup) findViewById(R.id.layoutDeleteNoteContainer)
            );
            builder.setView(view);
            dialogDeleteNote = builder.create();
            if(dialogDeleteNote.getWindow()!=null){
//                dialogDeleteNote.getWindow().setBackgroundDrawable(new colorDrawable(0));
            }
            view.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    class DeleteNoteTask extends AsyncTask<Void, Void, Void>{

                        @Override
                        protected Void doInBackground(Void... voids) {
                            NotesDatabase.getDatabase(getApplicationContext()).noteDao().deleteNote(alreadyavailableNote);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void unused) {
                            super.onPostExecute(unused);
                            Intent intent = new Intent();
                            intent.putExtra("isNoteDeleted",true);
                            setResult(RESULT_OK,intent);
                            finish();
                        }
                    }
                    new DeleteNoteTask().execute();
                }
            });
            view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogDeleteNote.dismiss();
                }
            });
        }
        dialogDeleteNote.show();
    }

}