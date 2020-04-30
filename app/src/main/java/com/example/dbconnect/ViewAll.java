package com.example.dbconnect;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class ViewAll extends AppCompatActivity {
     private ImageButton edit,delete;
      String myText;
     private TextView txt;

    DbHandling myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        myDB =new DbHandling(this);

        edit=findViewById(R.id.update);
        delete=findViewById(R.id.delete);
        txt=findViewById(R.id.textView);





    }

    @Override
    protected void onResume() {
        super.onResume();



        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder MyDialog= new AlertDialog.Builder(ViewAll.this);
                MyDialog.setTitle("Enter PizzaID");

                final EditText ID= new EditText(ViewAll.this);
                ID.setInputType(InputType.TYPE_CLASS_PHONE);
                MyDialog.setView(ID);

                MyDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        myText=ID.getText().toString();

                        openViewPage1();


                    }
                });

                MyDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                MyDialog.show();
            }
        });



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder MyDialog= new AlertDialog.Builder(ViewAll.this);
                MyDialog.setTitle("Enter PizzaID");

                final EditText ID= new EditText(ViewAll.this);
                ID.setInputType(InputType.TYPE_CLASS_PHONE);
                MyDialog.setView(ID);

                MyDialog.setPositiveButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        myText=ID.getText().toString();
                        myDB.delete(myText);
                        Toast.makeText(ViewAll.this,"Data Deleted",Toast.LENGTH_LONG).show();



                    }
                });

                MyDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                MyDialog.show();
            }
        });


        Cursor cursor =myDB.ViewData();

        if(cursor != null){

            StringBuilder stringBuilder=new StringBuilder();

            while(cursor.moveToNext()){

                stringBuilder.append(
                        "\nItem ID: " +cursor.getInt(0)+"\n"
                                +"\nItem Name: "+cursor.getString(1)
                                +"\nSmall Price: "+cursor.getString(2)
                                +"\nMedium Price: "+cursor.getString(3)
                                +"\nLarge Price: "+cursor.getString(4)
                                +"\nIngredients: "+cursor.getString(5)+"\n");

            }
            txt.setText(stringBuilder);
        }else{
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Error");
            builder.setMessage("No students Available");
            builder.show();
        }



    }

    private void openViewPage1() {
        Intent intent=new Intent(this,Update.class);
        startActivity(intent);
    }
}
