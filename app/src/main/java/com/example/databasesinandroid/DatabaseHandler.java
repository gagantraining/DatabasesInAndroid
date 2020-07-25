package com.example.databasesinandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    final static int DATABASE_VERSION = 1;
    final static String DATABASE_NAME = "MyDatabase";
    final static String TABLE_NAME = "student";
    Student s;

    public DatabaseHandler(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_table_query = "CREATE TABLE "+TABLE_NAME+"(" +
                                    "Roll INTEGER PRIMARY KEY,"+
                                    "Name VARCHAR(35),"+
                                    "Gender VARCHAR(6),"+
                                    "Marks NUMBER(5,2))";
        sqLiteDatabase.execSQL(create_table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String delete_table_query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        sqLiteDatabase.execSQL(delete_table_query);
        onCreate(sqLiteDatabase);
    }

    boolean insertData(int roll,String name,String gender,float marks){
        SQLiteDatabase db = this.getWritableDatabase();
        /*
        String insert_query ="INSERT INTO "+TABLE_NAME+" " +
                "values("+roll+",'"+name+"','"+gender+"',"+marks+")";
        // INSERT INTO student values(1,'Ram','Male',53.6);
        db.execSQL(insert_query);*/
        ContentValues cv = new ContentValues();
        cv.put("Roll",roll);
        cv.put("Name",name);
        cv.put("Gender",gender);
        cv.put("Marks",marks);
        int i = (int) db.insert(TABLE_NAME,null,cv);
        return i!=-1;
    }
    List<Student> getAllData(){
        List<Student> allStud = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String fetch_query = "SELECT * FROM "+TABLE_NAME;
        Cursor cur = db.rawQuery(fetch_query,null);

        if(cur.moveToFirst()){
               do{
                   s = new Student();
                   s.setRoll(cur.getInt(0));
                   s.setName(cur.getString(1));
                   s.setGender(cur.getString(2));
                   s.setMarks(cur.getFloat(3));
                   allStud.add(s);
               }while (cur.moveToNext());
        }
        cur.close();
        db.close();
        return allStud;
    }
}
