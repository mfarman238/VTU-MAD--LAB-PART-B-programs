package com.example.medicinedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    EditText txtMedicineName, txtDate, txtTime;
    Button btnSave, btnShow;
    TextView lblData;
    myDataBase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMedicineName = (EditText)findViewById(R.id.mName);
        txtDate = (EditText)findViewById(R.id.mDate);
        txtTime = (EditText)findViewById(R.id.mTime);
        btnSave = (Button) findViewById(R.id.btnInsert);
        btnSave.setOnClickListener(this);
        btnShow = (Button) findViewById(R.id.btnShow);
        btnShow.setOnClickListener(this);
        lblData = (TextView) findViewById(R.id.lbl_data1);

        myDatabase = new myDataBase(getBaseContext(),myDataBase.DATABASE_NAME,null,1);
        AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, showData.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(System.currentTimeMillis());
        time.add(Calendar.SECOND, 30);
        alarmMgr.set(AlarmManager.RTC_WAKEUP,time.getTimeInMillis(),pendingIntent);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnSave)){
            String medicineName = txtMedicineName.getText().toString();
            String date = txtDate.getText().toString();
            String time = txtTime.getText().toString();
            SQLiteDatabase database = myDatabase.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("NAME", medicineName);
            cv.put("MDATE", date);
            cv.put("MTIME", time);
            database.insert("MEDICINE_NAMES",null,cv);
            Toast.makeText(getBaseContext(),"Data_Saved",Toast.LENGTH_LONG).show();
        }
        else if(v.equals(btnShow))
        {




            lblData = (TextView)findViewById(R.id.lbl_data1);
            SQLiteDatabase database = myDatabase.getReadableDatabase();
            Cursor cursor = database.query("MEDICINE_NAMES",new String[]{"NAME","MDATE","MTIME"},null,null,null,null,null);
            lblData.setText("NAME\tDATE\tTIME\n");
            while(cursor.moveToNext())
            {
                lblData.append(cursor.getString(0)+"\t");
                lblData.append(cursor.getString(1)+"\t");
                lblData.append(cursor.getString(2)+"\t");
            }
        }
    }

}
