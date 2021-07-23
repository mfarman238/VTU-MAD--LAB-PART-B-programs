package com.example.clipboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1, b2;
    EditText t1, t2;
    ClipboardManager myclipboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = findViewById(R.id.TxtCopy);
        t2 = findViewById(R.id.TxtPaste);
        b1 = findViewById(R.id.BtnCopy);
        b2 = findViewById(R.id.BtnPaste);
        myclipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
		
		b2.setEnabled(false);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(t1.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "Text Field is empty", Toast.LENGTH_SHORT).show();
                }
                else {

                    ClipData myClip;
                    String data = t1.getText().toString();
                    myClip = ClipData.newPlainText("text", data);
                    myclipboard.setPrimaryClip(myClip);
		    b2.setEnabled(true);
                    Toast.makeText(MainActivity.this, "copied", Toast.LENGTH_SHORT).show();
                }

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                ClipData abc = myclipboard.getPrimaryClip();
                ClipData.Item item = abc.getItemAt(0);
                t2.setText(item.getText().toString());
                Toast.makeText(MainActivity.this, "pasted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}