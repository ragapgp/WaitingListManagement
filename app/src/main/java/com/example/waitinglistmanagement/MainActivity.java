package com.example.waitinglistmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
/* Home page for the administrator to implement operations on student courses waiting list.*/
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FragmentActivity";
    DBManage dbm;
    Button addstudent, editstudent, removestudent, waitinglist;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbm = new DBManage(this);

        addstudent = (Button) findViewById(R.id.button_addstudent);
        editstudent = (Button) findViewById(R.id.button_editstudent);
        removestudent = (Button) findViewById(R.id.button_removestudent);
        waitinglist = (Button) findViewById(R.id.button_viewwaitinglist);
        //displayData();

        //add student operation
        addstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        //remove student operation
        removestudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RemoveStudentActivity.class);
                startActivity(intent);
                finish();
            }
        });

        editstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //boolean isUpdate = dbm.updateData()
                Intent intent = new Intent(MainActivity.this, EditStudentActivity.class);
                startActivity(intent);
                finish();
            }
        });

        waitinglist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   //displayData();
                    Intent intent = new Intent(MainActivity.this, WaitingListActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

    private void displayData() {
        Log.d(TAG, "ListView : Displaying all the data");
        Cursor cursor = dbm.showAllData();
        if (cursor.getCount() == 0) {
            showToast("Error", "Nothing to remove.");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        ArrayList<String> arrayList = new ArrayList<>();

        while (cursor.moveToNext()) {
            arrayList.add(cursor.getString(0));
            arrayList.add(cursor.getString(1));
            arrayList.add(cursor.getString(2));
            arrayList.add(cursor.getString(3));
            arrayList.add(cursor.getString(4));
            //buffer.append("Id :" + cursor.getString(0) + "\n");
            //buffer.append("Name :" + cursor.getString(1) + "\n");
            //buffer.append("Course :" + cursor.getString(2) + "\n");
            //buffer.append("Status :" + cursor.getString(3) + "\n");
            //buffer.append("Priority :" + cursor.getString(4) + "\n");
        }
        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(listAdapter);

        // display all the data
        showToast("List", buffer.toString());
    }

    public void showToast(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
    }
}