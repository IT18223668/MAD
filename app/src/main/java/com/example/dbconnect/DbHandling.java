package com.example.dbconnect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import androidx.annotation.Nullable;


public class DbHandling extends SQLiteOpenHelper {

    public static final String  DBname="pizzaTable.db3";
    public static final String Table="pizza_table";
    public static final String column_1="ID";
    public static final String column2="Name";
    public static final String column3="Small";
    public static final String column4="Medium";
    public static final String column5="Large";
    public static final String column6="Ingredients";

    public DbHandling(@Nullable Context context) {
        super(context, DBname, null, 1);

        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Table + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT ,Small INTEGER,Medium INTEGER,Large INTEGER,Ingredients TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS "+ Table);
        onCreate(db);

    }

    public boolean add(String name,String small,String medium,String large,String ingredients){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put(column2,name);
        contentValues.put(column3,small);
        contentValues.put(column4,medium);
        contentValues.put(column5,large);
        contentValues.put(column6,ingredients);

        long result=db.insert(Table,null,contentValues);

        if (result == -1){
            return false;
        }
        else
            return true;
    }

    public Cursor ViewData(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(" select * from " + Table,null);

        return cursor ;

    }

    public Cursor Pizza(String ID){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(" select * from " + Table + " where "+column_1 + " = "+ ID ,null);

        return cursor ;
    }

    public Integer delete(String ID){
        SQLiteDatabase db=this.getWritableDatabase();
        return  db.delete(Table," ID=? ", new String[] {ID});
    }

    public boolean update(String ID,String name,String small,String medium,String large,String ingredients){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(column_1,ID);
        contentValues.put(column2,name);
        contentValues.put(column3,small);
        contentValues.put(column4,medium);
        contentValues.put(column5,large);
        contentValues.put(column6,ingredients);

        db.update(Table,contentValues,"ID=?",new String[] {ID});

        return true;
    }
}
