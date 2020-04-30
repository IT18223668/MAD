package com.example.dbconnect;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Update extends AppCompatActivity {
    Button btupdate;
    EditText id, name, small, medium, large, ingredients;
    DbHandling myDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);

        myDB = new DbHandling(this);

        btupdate = findViewById(R.id.button);

        id = findViewById(R.id.editText2);
        name = findViewById(R.id.editText);
        small = findViewById(R.id.editText4);
        medium = findViewById(R.id.editText5);
        large = findViewById(R.id.editText8);
        ingredients = findViewById(R.id.editText10);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDB.update(id.getText().toString(),
                        name.getText().toString(),
                        small.getText().toString(),
                        medium.getText().toString(),
                        large.getText().toString(),
                        ingredients.getText().toString());

                if (isUpdate == true) {
                    Toast.makeText(Update.this, "Data Updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Update.this, "Data doesn't update", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
