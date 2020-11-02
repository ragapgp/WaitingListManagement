package com.example.waitinglistmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
/* Add student to the existing waiting list.*/
public class AddStudentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText id, name, course, status, priority;
    Button addstudent, back;
    SQLiteDatabase sqLiteDatabase;
    DBManage dbm;
    private Spinner spinnerpriority;
    String[] assignpriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        dbm = new DBManage(this);

        //define elements
        name = (EditText) findViewById(R.id.studentname);
        course = (EditText) findViewById(R.id.coursename);
        id = (EditText) findViewById(R.id.studentid);
        status = (EditText) findViewById(R.id.status);
        addstudent = (Button) findViewById(R.id.button_addstudent);
        back = (Button) findViewById((R.id.button_homepage));
        //priority = (EditText) findViewById(R.id.priority);
        spinnerpriority = findViewById(R.id.priorityspinner);

        spinnerpriority.setOnItemSelectedListener(this);

        String[] assignpriority = getResources().getStringArray(R.array.priority);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, assignpriority);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerpriority.setAdapter(adapter);
        InsertData();

        //sqLiteDatabase = openOrCreateDatabase("Student_Info", Context.MODE_PRIVATE, null);
        //sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS student(name VARCHAR, course VARCHAR, email VARCHAR, status VARCHAR, priority VARCHAR)");

        //loadSpinnerInfo();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddStudentActivity.this, MainActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }



        public void InsertData(){
            addstudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String sname = name.getText().toString();
                    final String sid = id.getText().toString();
                    final String sstatus = status.getText().toString();
                    final String scourse = course.getText().toString();
                    final String sspinner = spinnerpriority.getSelectedItem().toString();

                    System.out.println("name values are :"  + sname);
                    System.out.println("course values are :"  + scourse);
                    System.out.println("id values are :"  + sid);
                    System.out.println("status values are :"  + sstatus);
                    System.out.println("spinnerpriority values are :"  + sspinner);

                    boolean isTrue = dbm.addData(sid, sname, scourse, sstatus, sspinner);
                    if (isTrue = true) {
                        Toast.makeText(getApplicationContext(), "Data added successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Data not added", Toast.LENGTH_LONG).show();
                    }

                    //sqLiteDatabase.execSQL("INSERT INTO student VALUES('"+name.getText()+"', '"+course.getText()+"','" + status.getText()+"', '" + priority.getText()+"');");
                    //sqLiteDatabase.execSQL("INSERT INTO student VALUES('"+sid+"', '"+sname+"', '"+scourse+"','" + sstatus+"', '" + sspinner+"');");
                    System.out.println("Record added successfully");
                    //clearForm((ViewGroup) findViewById(R.id.root));
                }
            });
        }

   /* private void clearForm(ViewGroup viewById) {
        for (int i = 0, count = viewById.getChildCount(); i < count; ++i) {
            View view = viewById.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
                assignpriority.ge
                ;
            }

            if(view instanceof ViewGroup && (((ViewGroup)view).getChildCount() > 0))
                clearForm((ViewGroup)view);
        }
    }*/


    private void loadSpinnerInfo() {
        //DatabaseH
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId() == R.id.priorityspinner) {
            String value = adapterView.getItemAtPosition(i).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}