package com.example.joker.blogpost.SQLHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by joker on 4/3/18.
 */

public class SQLiteHelperClass extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "blogDB.db";

    public SQLiteHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_QUERY = " CREATE TABLE "+ ContractorClass.BlogPost.TABLE_NAME + " ( " +
                ContractorClass.BlogPost._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ContractorClass.BlogPost.USER_NAME + " TEXT NOT NULL, " +
                ContractorClass.BlogPost.POST_TITLE + " TEXT NOT NULL, " +
                ContractorClass.BlogPost.IMAGE_URI + " TEXT NOT NULL " +
                ");";

        db.execSQL(CREATE_TABLE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + ContractorClass.BlogPost.TABLE_NAME);
        onCreate(db);

    }
}
