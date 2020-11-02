package com.example.waitinglistmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
/* Edit student data and add to the waiting list.*/
public class EditStudentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText id, name, course, status, priority;
    Button update, back;
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
        setContentView(R.layout.activity_edit_student);

        //context = this;

        dbm = new DBManage(this);
        back = (Button) findViewById(R.id.button_homepage);
        update = (Button) findViewById(R.id.button_updatestudent);
        name = (EditText) findViewById(R.id.studentname);
        course = (EditText) findViewById(R.id.coursename);
        id = (EditText) findViewById(R.id.studentid);
        status = (EditText) findViewById(R.id.status);
        //priority = (EditText) findViewById(R.id.priority);
        spinnerpriority = findViewById(R.id.priorityspinner);

        spinnerpriority.setOnItemSelectedListener(this);

        String[] assignpriority = getResources().getStringArray(R.array.priority);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, assignpriority);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerpriority.setAdapter(adapter);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean isUpdate = dbm.updateData(id.getText().toString(),
                       name.getText().toString(), course.getText().toString(), status.getText().toString(), spinnerpriority.getSelectedItem().toString());

               if(isUpdate == true)
                   Toast.makeText(getApplicationContext(), "Student updated successfully", Toast.LENGTH_LONG).show();
               else
                   Toast.makeText(getApplicationContext(), "Student not updated", Toast.LENGTH_LONG).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditStudentActivity.this, MainActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

    }
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId() == R.id.priorityspinner) {
            String value = adapterView.getItemAtPosition(i).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}