package com.example.emicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText principle, rate, downpayment, term, etEMI;
    Button compute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        principle = findViewById(R.id.etp);
        downpayment = findViewById(R.id.etd);
        term = findViewById(R.id.ett);
        rate = findViewById(R.id.etr);
        compute = findViewById(R.id.btn);
        etEMI = findViewById(R.id.etEMI);

        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float p,d,r,t;

                p = Float.valueOf(principle.getText().toString());
                d = Float.valueOf(downpayment.getText().toString());
                float balanceP = p-d;
                r  = Float.valueOf(rate.getText().toString())/12/100;
                t = Float.valueOf(term.getText().toString());

                float emi = (float) (balanceP * r * (Math.pow(1+r, t))/(Math.pow(1+r, t) - 1));

                etEMI.setText("EMI is :" + emi);

            }
        });
    }
}