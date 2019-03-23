package com.example.kaan.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Created by kaan on 08/04/15.
 */
public class DatabaseOperations {

    public static abstract class Info implements BaseColumns {

        private Database database;
        private Context context;
        private SQLiteDatabase sqLiteDatabase;

        public static final int database_version = 1;
        public static final String Key_id = "id";
        public static final String Database_Name = "Stores_info";
        public static final String Table_Name = "stores";
        public static final String StoreName = "StoreName";
        public static final int Bulunduğu_Kat = 1;
        public static final String Kat = Integer.toString(Bulunduğu_Kat);
        public static final String Yanındaki_mağazalar = "Mağaza";
        public static final String Karşıdaki_mağaza = "K.Mağaza";
        public static final String Yol_Tarifi = "Yol";

        public Info(Context c) {
            context = c;
        }

        public Info open() {

            database = new Database(context);
            sqLiteDatabase = database.getWritableDatabase();
            return this;
        }

        public long createEntry(String store, String kat, String yanındaki_mağazalar, String karşıdaki_mağaza, String yol_Tarifi) {

            ContentValues cv = new ContentValues();
            cv.put(StoreName, store);
            cv.put(Kat, kat);
            cv.put(Yanındaki_mağazalar, yanındaki_mağazalar);
            cv.put(Karşıdaki_mağaza, karşıdaki_mağaza);
            cv.put(Yol_Tarifi, yol_Tarifi);
            return sqLiteDatabase.insert(Table_Name, null, cv);
        }

        public HashMap<String, String> getInfo(String name) {

            HashMap<String, String> store = new HashMap<String, String>();
            String Query = "SELECT * FROM " + Table_Name + " WHERE name=" + name;

            SQLiteDatabase db = this.database.getReadableDatabase();
            Cursor cursor = db.rawQuery(Query, null);

            cursor.moveToFirst();
            if (cursor.getCount() > 0) {

                store.put(StoreName, cursor.getString(1));
                store.put(Kat, cursor.getString(2));
                store.put(Yanındaki_mağazalar, cursor.getString(3));
                store.put(Karşıdaki_mağaza, cursor.getString(4));
                store.put(Yol_Tarifi, cursor.getString(5));
            }
            cursor.close();
            db.close();

            return store;
        }

        public ArrayList<HashMap<String, String>> getAllInfo() {


            SQLiteDatabase db = this.database.getReadableDatabase();
            String Query = "SELECT * FROM " + Table_Name;
            Cursor cursor = db.rawQuery(Query, null);
            ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<String,String>();
                    for (int i = 0; i < cursor.getColumnCount(); i++) {
                        map.put(cursor.getColumnName(i), cursor.getString(i));
                    }

                    list.add(map);
                } while (cursor.moveToNext());
            }
            db.close();

            return list;
        }
    }
}
