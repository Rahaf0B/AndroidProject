package com.example.androidproject;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    private  String Creat_User_Table="CREATE TABLE IF NOT EXISTS User ( UserID INTEGER PRIMARY KEY AUTOINCREMENT, Fname TEXT NOT NULL, Lname TEXT NOT NULL, Email TEXT NOT NULL,Password TEXT NOT NULL,ContinenttravelDestination TEXT NOT NULL);";
    private  String Drop_User_Table="DROP TABLE IF EXISTS User";

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version); }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Creat_User_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Drop_User_Table);

        onCreate(sqLiteDatabase);


    }


    public void insertUser(User user){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Fname",user.getFname());
        contentValues.put("Lname",user.getLname());
        contentValues.put("Email",user.getEmail());
        contentValues.put("Password",user.getPassword());
        contentValues.put("ContinenttravelDestination",user.getDistination());


        sqLiteDatabase.insert("User", null, contentValues);
    }




    public Cursor getAllUser()
    { SQLiteDatabase sqLiteDatabase = getReadableDatabase();
      //  return sqLiteDatabase.rawQuery("SELECT * FROM User", null);
Cursor alluser= sqLiteDatabase.rawQuery("SELECT * FROM User", null);
        while (alluser.moveToNext()){
        System.out.println(
                    "Id= "+alluser.getString(0)
                            +"\n=== "+alluser.getString(1)
                            +"\n=== "+alluser.getString(2)
                            +"\n==== "+alluser.getString(3)
                            +"\n==== "+alluser.getString(4)
                            +"\n\n"
            );}
            return sqLiteDatabase.rawQuery("SELECT * FROM User", null);
    }




    public  Cursor getPrefiredDestination(Integer Id)
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT ContinenttravelDestination FROM User WHERE ( UserID=" +Id+")", null);
    }

    public boolean checkIfEmailExists(String Email) {
        System.out.println(Email);
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM User WHERE Email='" + Email+ "'", null);

        System.out.println(cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                String Password = cursor.getString(0);
                System.out.println("-------------------------------------------------------------------");
                System.out.println(cursor.getCount());

            } while (cursor.moveToNext());
        }
        if (cursor.getCount() == 0) {
            return true;
        } else {
            return false;
        }
    }



    public String checkIfPasswordExists(String Email) {
        String quaryString = "SELECT Password FROM User WHERE Email='" + Email+ "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(quaryString, null);
        List<String> list=new ArrayList<String>();
        if (cursor.moveToFirst()) {
            do {
                String Password = cursor.getString(0);
                System.out.println("-------------------------------------------------------------------");
                System.out.println(Password);
                list.add(Password);
            } while (cursor.moveToNext());
        }

        System.out.println(list.size()+"..................................................................");
        cursor.close();
        db.close();
        System.out.println(list.toString());
        String pass="NO MATCH";
        if (list.size()>0){
            pass=list.get(0);}
        else
            pass="NOMATch AT ALLL OK ";
        return pass;
    }






}
