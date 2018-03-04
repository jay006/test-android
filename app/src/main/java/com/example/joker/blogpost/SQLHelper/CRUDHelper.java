package com.example.joker.blogpost.SQLHelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by joker on 4/3/18.
 */

public final class CRUDHelper {

    //method to insertData to SQLite database
    public static long insertData(SQLiteDatabase database, String userName, String title, String imageUri) {

        ContentValues cv = new ContentValues();
        cv.put(ContractorClass.BlogPost.USER_NAME,userName);
        cv.put(ContractorClass.BlogPost.POST_TITLE,title);
        cv.put(ContractorClass.BlogPost.IMAGE_URI,imageUri);
        long i = database.insert(ContractorClass.BlogPost.TABLE_NAME,null,cv);
        cv.clear();

        return i ;
    }


    //method to getAll the data from database
    public static Cursor getAllPost(SQLiteDatabase db) {

        return db.query(ContractorClass.BlogPost.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                ContractorClass.BlogPost._ID);
    }


    public static void deletePost(long id, SQLiteDatabase database) {
        database.delete(ContractorClass.BlogPost.TABLE_NAME,ContractorClass.BlogPost._ID+" = "+id,null);
    }
}
