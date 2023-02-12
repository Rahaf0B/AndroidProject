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
    private  String Creat_Favorite_Table="CREATE TABLE IF NOT EXISTS Favorite ( CityID INTEGER PRIMARY KEY AUTOINCREMENT, City TEXT NOT NULL, Country TEXT NOT NULL, User_ID INTEGER NOT NULL,FOREIGN KEY (User_ID) REFERENCES User (UserID));";

    private  String Drop_User_Table="DROP TABLE IF EXISTS User";
    private  String Drop_Favorite_Table="DROP TABLE IF EXISTS Favorite";


    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version); }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Creat_User_Table);
        sqLiteDatabase.execSQL(Creat_Favorite_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Drop_User_Table);
        sqLiteDatabase.execSQL(Drop_Favorite_Table);
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
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            return sqLiteDatabase.rawQuery("SELECT * FROM User", null);
    }


        public Cursor getUser(int id){
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor userdata= sqLiteDatabase.rawQuery("SELECT * FROM User WHERE (UserID="+id+")", null);

        return userdata;
        }


    public int getUserId(String Email){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        int ID=-1;
        Cursor user=sqLiteDatabase.rawQuery("SELECT UserID FROM User WHERE Email='" + Email+ "'",null);
        while (user.moveToNext()){
            ID=user.getInt(0);
        }
        return  ID;
    }



    public String getUserNme(String Email){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String fname="";
        String lname="";
        Cursor userfname=sqLiteDatabase.rawQuery("SELECT Fname FROM User WHERE Email='" + Email+ "'",null);

        Cursor userlname=sqLiteDatabase.rawQuery("SELECT Lname FROM User WHERE Email='" + Email+ "'",null);

        while (userfname.moveToNext()){
            fname=userfname.getString(0);
        }
        while (userlname.moveToNext()){
            lname=userlname.getString(0);
        }
        String name=fname+" "+lname;
        return  name;
    }




    public  String getPrefiredDestination(Integer Id)
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String Prefdestination="";
        Cursor destination= sqLiteDatabase.rawQuery("SELECT ContinenttravelDestination FROM User WHERE ( UserID=" +Id+")", null);
        while ((destination.moveToNext())){
            Prefdestination=destination.getString(0);
        }

        return Prefdestination;
    }

    public boolean checkIfEmailExists(String Email) {

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM User WHERE Email='" + Email+ "'", null);



        if (cursor.getCount() == 0) {
            return true;
        } else {
            return false;
        }
    }


    public void UpdateFName(String Name,int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("Fname",Name);


        sqLiteDatabase.update("User", cv, "UserID = ?", new String[]{Integer.toString(id)});

        Cursor alluser=getUser(id);
        sqLiteDatabase.close();


    }
    public void UpdateLName(String Name,int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Lname",Name);
        sqLiteDatabase.update("User", cv, "UserID = ?", new String[]{Integer.toString(id)});
        sqLiteDatabase.close();
    }
    public void UpdateEmail(String Email, int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Email",Email);
        sqLiteDatabase.update("User", cv, "UserID=?" , new String[]{Integer.toString(id)});
        sqLiteDatabase.close();


    }
    public void UpdatePass(String pass, int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Password",pass);
        sqLiteDatabase.update("User", cv, "UserID = ?", new String[]{Integer.toString(id)});
        sqLiteDatabase.close();

    }
    public void UpdateDestination(String destination,int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ContinenttravelDestination",destination);
        sqLiteDatabase.update("User", cv, "UserID = ?", new String[]{Integer.toString(id)});
        sqLiteDatabase.close();
    }


    public String checkIfPasswordExists(String Email) {
        String quaryString = "SELECT Password FROM User WHERE Email='" + Email+ "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(quaryString, null);
        List<String> list=new ArrayList<String>();
        if (cursor.moveToFirst()) {
            do {
                String Password = cursor.getString(0);

                list.add(Password);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        String pass="NO MATCH";
        if (list.size()>0){
            pass=list.get(0);}
        else
            pass="NOMATch AT ALLL OK ";
        return pass;
    }



    public Cursor getAllFavorite(int User_ID)
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        return sqLiteDatabase.rawQuery("SELECT * FROM Favorite WHERE (User_ID="+User_ID+")", null);
    }

    public void DeleteFavorite(String city, int User_ID){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("Favorite", "City = ? AND User_ID= ?", new String[]{city,String.valueOf(User_ID)});
        sqLiteDatabase.close();
    }

    public void insertFavorite(String city_name, String country_name, int user_id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("City",city_name);
        contentValues.put("Country",country_name);
        contentValues.put("User_ID",user_id);

        sqLiteDatabase.insert("Favorite ", null, contentValues);
    }
    public boolean CheckIfFavoriteIsExist(String city, int User_ID) {
        Cursor cursor = null;
        String selectString = "select * from Favorite where (User_ID = "+User_ID+")"+" AND City like '" + city + "'";
        SQLiteDatabase sqliteDatabase = getReadableDatabase();
        cursor = sqliteDatabase.rawQuery(selectString, null);
        if (cursor.getCount() > 0){
            cursor.close();
            return true;
        }else {
            cursor.close();
            return false;
        }
    }
}
