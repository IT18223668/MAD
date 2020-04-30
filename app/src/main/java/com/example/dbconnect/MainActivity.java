package com.example.dbconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,priceS,priceM,priceL,Ingred;
    Button Add,selectA;

    DbHandling myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB =new DbHandling(this);

        name=findViewById(R.id.name);
        priceS=findViewById(R.id.priceS);
        priceM=findViewById(R.id.priceM);
        priceL=findViewById(R.id.priceL);
        Ingred=findViewById(R.id.ing);

        Add=findViewById(R.id.Add);
        selectA=findViewById(R.id.button2);


    }

    @Override
    protected void onResume() {
        super.onResume();

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted=myDB.add(name.getText().toString(),priceS.getText().toString(),priceM.getText().toString(),priceL.getText().toString(),Ingred.getText().toString());

                if (isInserted=true)
                    Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                    Toast.makeText(MainActivity.this,"Data does not Inserted",Toast.LENGTH_LONG).show();


            }
        });

        selectA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewPage();
            }
        });
    }

    private void openViewPage() {
        Intent intent=new Intent(this,ViewAll.class);
        startActivity(intent);
    }
}
