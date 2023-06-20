package com.example.gestionnairenote;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText etTitle, etContent;
    private Button btnAddNote;
    private ListView listViewNotes;
    private ArrayAdapter<Note> notesAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        etTitle = findViewById(R.id.editTitle);
        etContent = findViewById(R.id.editContent);
        btnAddNote = findViewById(R.id.btnAddNote);
        listViewNotes = findViewById(R.id.listViewNotes);

        databaseHelper = new DatabaseHelper(this);
        refreshNotesList();

        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString().trim();
                String content = etContent.getText().toString().trim();

                if (title.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a title", Toast.LENGTH_SHORT).show();
                    return;
                }

                Note note = new Note();
                note.setTitle(title);
                note.setContent(content);

                databaseHelper.addNote(note);
               refreshNotesList();
            }
        });

        listViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note selectedNote = (Note) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "Clicked: " + selectedNote.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void refreshNotesList() {
        List<Note> notes = databaseHelper.getAllNotes();
        notesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        listViewNotes.setAdapter(notesAdapter);
    }
}