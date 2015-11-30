package by.amelkov.androidnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import by.amelkov.androidnotes.model.Note;

public class MainActivity extends AppCompatActivity {

    public final static int REQ_CODE_CHILD = 1;

    ArrayList<Note> list;
    Button btnAdd;
    EditText editText;
    ArrayAdapter<Note> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<Note>();
        ListView notesList = (ListView) findViewById(R.id.notesList);
        adapter = new ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1,list);

        notesList.setAdapter(adapter);
        notesList.setOnCreateContextMenuListener(this);

        list.add(new Note("first test note"));
        list.add(new Note("second test note"));
        list.add(new Note("third test note"));

        btnAdd = (Button) findViewById(R.id.btnAdd);
        editText = (EditText) findViewById(R.id.editText);

        View.OnClickListener oclBtnAdd = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = editText.getText().toString();
                list.add(new Note(tmp));
                editText.setText("");
            }
        };

        btnAdd.setOnClickListener(oclBtnAdd);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo aMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;

        final int position = aMenuInfo.position;
        final Note data = adapter.getItem(aMenuInfo.position);

        menu.add("Edit note").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                data.setId(position);
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("note", data);
                startActivityForResult(intent, REQ_CODE_CHILD);
                return true;
            }
        });
        menu.add("Delete note").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                list.remove(data);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Note was deleted", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        if(requestCode == REQ_CODE_CHILD) {
            switch (resultCode) {
                case RESULT_OK:
                    Note note = (Note) data.getExtras().getSerializable("note");
                    list.set(note.getId(), note);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "Note was updated", Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    }
}
