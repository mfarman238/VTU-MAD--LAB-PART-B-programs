package com.example.medicinedatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class showData extends AppCompatActivity {

    TextView lblData;
    myDataBase myDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);


        lblData = (TextView)findViewById(R.id.lbl_data2);
        SQLiteDatabase database = myDataBase.getReadableDatabase();
        Cursor cursor = database.query("MEDICINE_NAMES",new String[]{"NAME","MDATE","MTIME"},null,null,null,null,null);
        lblData.setText("NAME\tDATE\tTIME\n");
        while (cursor.moveToNext())
        {
            lblData.append(cursor.getString(0)+"\t");
            lblData.append(cursor.getString(1)+"\t");
            lblData.append(cursor.getString(2)+"\t");
        }

    }
}
