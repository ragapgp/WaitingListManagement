package com.example.waitinglistmanagement;

import android.content.Context;

import java.util.ArrayList;

public class TableView {
    Context context;
    private String[] items = {"ID", "Name", "Course", "Status", "Priority"};
    private String[][] itemList;

    public TableView(Context context){
        this.context = context;
    }

    public String[] getItems(){
        return items;
    }

    public String[][] getItemList(){
       // ArrayList<Data> data = new DBAdapter(context).re
        return itemList;
    }
}
