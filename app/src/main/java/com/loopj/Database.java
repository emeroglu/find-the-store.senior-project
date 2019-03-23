package com.example.kaan.project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.Cursor;

/**
 * Created by kaan on 08/04/15.
 */
public class Database extends SQLiteOpenHelper{


    public Database(Context context ) {
        super(context, DatabaseOperations.Info.Database_Name, null, DatabaseOperations.Info.database_version);
        Log.d("Database", "Table is created");
    }

    public void onCreate(SQLiteDatabase sdb){

        String Query = "Create Table" + DatabaseOperations.Info.Table_Name + "(" +
                DatabaseOperations.Info.Key_id + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseOperations.Info.StoreName + "TEXT," +
                DatabaseOperations.Info.Kat + "TEXT NOT NULL, " +
                DatabaseOperations.Info.Yanındaki_mağazalar + "TEXT," +
                DatabaseOperations.Info.Karşıdaki_mağaza + "TEXT, " +
                DatabaseOperations.Info.Yol_Tarifi + "TEXT );";
        sdb.execSQL(Query);
    }

    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion){

        DB.execSQL("DROP TABLE IF EXISTS " + DatabaseOperations.Info.Table_Name);
        onCreate(DB);
    }
}
