package com.example.smsreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String address = extras.getString("Mnum");
            String message = extras.getString("Message");
            TextView senderNum = (TextView) findViewById(R.id.address);
            TextView messageBody = (TextView) findViewById(R.id.message);
            senderNum.setText("Message From :" +address);
            messageBody.setText("Message :\n" +message);
        }
    }
}