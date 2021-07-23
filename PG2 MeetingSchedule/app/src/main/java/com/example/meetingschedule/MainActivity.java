package com.example.meetingschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText time, date, agenda;
    Button submit;
    DBHandler DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = findViewById(R.id.etMTime);
        date = findViewById(R.id.etMdate);
        agenda = findViewById(R.id.etMAgenda);
        submit = findViewById(R.id.btnSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mTime, mDate, mAgenda;
                mTime = time.getText().toString();
                mDate = date.getText().toString();
                mAgenda = agenda.getText().toString();

                boolean check = DB.insert(mTime, mDate, mAgenda);

                if (check == true)
                    Toast.makeText(MainActivity.this, "Meeting Schedule Succesfully",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Meeting Not Schedule",Toast.LENGTH_SHORT).show();



            }
        });


    }
}