package com.example.waitinglistmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
// Remove student implementation
public class RemoveStudentActivity extends AppCompatActivity {

    EditText id, name;
    Button remove, back;
    SQLiteDatabase sqLiteDatabase;
    DBManage dbm;
    private Spinner spinnerpriority;
    String[] assignpriority;
    ArrayList<String> list;
    ArrayAdapter adapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_student);
        context = this;

        //define layout elements
        dbm = new DBManage(this);
        id = (EditText) findViewById(R.id.studentid);
        name = (EditText) findViewById(R.id.studentname);
        remove = (Button) findViewById(R.id.button_removestudent);
        back = (Button) findViewById(R.id.button_homepage);

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deleteRow = dbm.deleteRecord(id.getText().toString());
                if (deleteRow > 0)
                    Toast.makeText(getApplicationContext(), "Student removed successfully", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "Student not removed", Toast.LENGTH_LONG).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RemoveStudentActivity.this, MainActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}