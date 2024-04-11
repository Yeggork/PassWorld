package com.example.passworld.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.passworld.non.Passworddd;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private Context context;
    private DBHelper dbHelper;

    SQLiteDatabase db;

    public DBManager(Context context){
        this.context = context;
        dbHelper = new DBHelper(this.context);
    }
    @SuppressLint("Range")
    public List<Passworddd> getPassword(){
        List<Passworddd> passworddds = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from "+ DBConst.PASSWORD_TABLE_NAME,null);
        while (cursor.moveToNext()){
            Passworddd password = new Passworddd();
            password.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBConst.PASSWORD_ID))));
            password.setTextpassword(cursor.getString(cursor.getColumnIndex(DBConst.PASSWORD_TEXT)));
            passworddds.add(password);
        }
        cursor.close();
        return passworddds;
    }
     public void addPassword(Passworddd passworddds){
         ContentValues cv = new ContentValues();
         cv.put(DBConst.PASSWORD_TEXT,passworddds.getTextpassword());
         db.insert(DBConst.PASSWORD_TABLE_NAME,null,cv);
     }
     public void deletePassword(Passworddd passworddds){
        db.delete(DBConst.PASSWORD_TABLE_NAME,DBConst.PASSWORD_ID + " = " + passworddds.getId(),null);
     }
     public void openDb(){
        db = dbHelper.getWritableDatabase();
     }
     public void closeDb(){
        db.close();
     }
}
