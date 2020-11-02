package com.example.waitinglistmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
/* View the student waiting list.*/
public class WaitingListActivity extends AppCompatActivity {

    EditText id, name, course, status, priority;
    Button back;
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
        setContentView(R.layout.activity_waiting_list);
        context = this;

        dbm = new DBManage(this);
        list = new ArrayList<>();
        back = (Button) findViewById(R.id.button_homepage);
        displayData();

        TableLayout tableLayout=(TableLayout)findViewById(R.id.tablelayout);
        // Add header row
        TableRow rowHeader = new TableRow(context);
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        String[] headerText={"ID","NAME","COURSE","STATUS","PRIORITY"};
        for(String c:headerText) {
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(15);
            tv.setPadding(3, 3, 3, 3);
            tv.setText(c);
            rowHeader.addView(tv);
        }
        tableLayout.addView(rowHeader);

        // Get data from sqlite database and add them to the table
        // Open the database for reading
        SQLiteDatabase db = dbm.getReadableDatabase();
        // Start the transaction.
        db.beginTransaction();

        try
        {
            //String selectQuery = "SELECT * FROM "+ dbm.ge;
            Cursor cursor = dbm.showAllData();
            //Cursor cursor = db.rawQuery(selectQuery,null);
            if(cursor.getCount() >0)
            {
                while (cursor.moveToNext()) {
                    // Read columns data
                    int outlet_id= cursor.getInt(cursor.getColumnIndex("ID"));
                    String outlet_name= cursor.getString(cursor.getColumnIndex("Name"));
                    String outlet_course= cursor.getString(cursor.getColumnIndex("Course"));
                    String outlet_status= cursor.getString(cursor.getColumnIndex("Status"));
                    String outlet_priority= cursor.getString(cursor.getColumnIndex("Priority"));

                    // dara rows
                    TableRow row = new TableRow(context);
                    row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                    String[] colText={outlet_id+"",outlet_name+"", outlet_course+"", outlet_status+"", outlet_priority};
                    for(String text:colText) {
                        TextView tv = new TextView(this);
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv.setGravity(Gravity.CENTER);
                        tv.setTextSize(16);
                        tv.setPadding(3, 3, 3, 3);
                        tv.setText(text);
                        row.addView(tv);
                    }
                    tableLayout.addView(row);

                }

            }
            db.setTransactionSuccessful();

        }
        catch (SQLiteException e)
        {
            e.printStackTrace();

        }
        finally
        {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WaitingListActivity.this, MainActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private void displayData() {
        Cursor cursor = dbm.showAllData();
        if (cursor.getCount() == 0) {
            showToast("Error", "Nothing to remove.");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            buffer.append("Id :" + cursor.getString(0) + "\n");
            buffer.append("Name :" + cursor.getString(1) + "\n");
            buffer.append("Course :" + cursor.getString(2) + "\n");
            buffer.append("Status :" + cursor.getString(3) + "\n");
            buffer.append("Priority :" + cursor.getString(4) + "\n");
        }

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