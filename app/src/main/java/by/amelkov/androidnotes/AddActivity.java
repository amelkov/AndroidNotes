package by.amelkov.androidnotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import by.amelkov.androidnotes.model.Note;

public class AddActivity extends AppCompatActivity {

    Button btnUpdate;
    EditText text;
    TextView lastDate;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Intent intent = getIntent();
        note = (Note) intent.getSerializableExtra("note");

        btnUpdate = (Button) findViewById(R.id.updateNote);
        text = (EditText) findViewById(R.id.editNote);
        lastDate = (TextView) findViewById(R.id.lastDate);

        text.setText(note.getText());
        lastDate.setText("Last update: "+note.getTime());

        View.OnClickListener oclBtnAdd = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = text.getText().toString();
                note.setText(tmp);
                note.setTimeCreate();
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent);
                resultIntent.putExtra("note", note);
                finish();
            }
        };

        btnUpdate.setOnClickListener(oclBtnAdd);
    }
}
