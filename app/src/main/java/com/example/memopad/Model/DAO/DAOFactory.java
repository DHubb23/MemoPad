package com.example.memopad.Model.DAO;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import androidx.annotation.Nullable;

public class DAOFactory extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mydatabase.db";
    public static final String TABLE_MEMOS = "memos";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MEMO = "memo";

    public static final String QUERY_CREATE_MEMO_TABLE = "CREATE TABLE memos ( _id INTEGER PRIMARY KEY AUTOINCREMENT, memo TEXT NOT NULL)";
    public DAOFactory(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_CREATE_MEMO_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMOS);
        onCreate(db);
    }
    public MemoDAO getMemoDao() {
        return new MemoDAO(this);
    }
}
