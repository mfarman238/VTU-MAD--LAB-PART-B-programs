package com.example.fileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    Button save, open;
    EditText fName, textArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fName = findViewById(R.id.etFName);
        textArea = findViewById(R.id.etTextPlace);
        save = findViewById(R.id.btnSave);
        open = findViewById(R.id.btnOpen);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileOutputStream fos = null;
                String text = textArea.getText().toString();
                Toast.makeText(MainActivity.this, "File Saved", Toast.LENGTH_SHORT).show();

                try {
                    fos = openFileOutput(fName.getText().toString(), MODE_PRIVATE);
                    fos.write(text.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    textArea.getText().clear();
                    fName.getText().clear();
                    if(fos != null){
                        try {
                            fos.close();
                        } catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }

            }
        });

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream fis = null;
                String fileName = fName.getText().toString();

                if(!fileName.isEmpty()){

                    try{
                        fis = openFileInput(fileName);
                        InputStreamReader isr = new InputStreamReader(fis);
                        BufferedReader br = new BufferedReader(isr);
                        StringBuilder sb = new StringBuilder();
                        String text;
                        Toast.makeText(MainActivity.this, "File Opened", Toast.LENGTH_SHORT).show();

                        while ((text = br.readLine()) != null){
                            sb.append(text).append("\n");
                        }
                        textArea.setText(sb.toString());
                    }catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });



    }
}