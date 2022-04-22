package com.example.mahe.attendease;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MAHE on 4/2/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "attendease.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE LOGIN( USERNAME VARCHAR PRIMARY KEY ,PWD VARCHAR,NAME VARCHAR,EMAIL VARCHAR,MOBILE VARCHAR,DEPT VARCHAR);");
        db.execSQL("CREATE TABLE ASSIGN( USERNAME  VARCHAR, COURSE_ID  VARCHAR, COURSE_NAME VARCHAR , STRENGTH INTEGER, MAX INTEGER, PRIMARY KEY(USERNAME,COURSE_ID) );");
        db.execSQL("CREATE TABLE STUDENT ( USERNAME VARCHAR, COURSE_ID VARCHAR, REG_NO VARCHAR, NAME VARCHAR, TAKEN INTEGER, ATTENDED INTEGER, ATTENDANCE REAL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertLoginEntry(String userName,String password,String name,String email,String mobile,String dept)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("USERNAME", userName);
        newValues.put("PWD",password);
        newValues.put("NAME",name);
        newValues.put("EMAIL",email);
        newValues.put("MOBILE",mobile);
        newValues.put("DEPT",dept);
        // Insert the row into your table
        db.insert("LOGIN", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }
    public void insertAssignEntry(String userName,String course_id,String course_name,int strength, int max)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("USERNAME", userName);
        newValues.put("COURSE_ID",course_id);
        newValues.put("COURSE_NAME",course_name);
        newValues.put("STRENGTH",strength);
        newValues.put("MAX",max);
        // Insert the row into your table
        db.insert("ASSIGN", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }
    public void insertStudentEntry(String userName,String course_id,String regno,String name, int taken, int attended, double attendance)
    {
        SQLiteDatabase db =this.getWritableDatabase();

        db.execSQL("INSERT INTO STUDENT VALUES('"+userName+"','"+course_id+"','"+regno+"','"+name+"',"+taken+","+attended+","+attendance+");");
    }
    public int deleteEntry(String UserName)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        //String id=String.valueOf(ID);
        String where="USERNAME=?";
        int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{UserName}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }
    public String getSinlgeEntry(String userName)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT PWD FROM LOGIN WHERE USERNAME='"+userName+"';",null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(0);
        cursor.close();
        return password;
    }
    public void  updateEntry(String userName,String password)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD",password);

        String where="USERNAME = ?";
        db.update("LOGIN",updatedValues, where, new String[]{userName});
    }
}
