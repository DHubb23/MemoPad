package com.example.memopad.Model.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.memopad.Model.Memo;

import java.util.ArrayList;
import java.util.List;

public class MemoDAO {
    private final DAOFactory daoFactory;
    MemoDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    public void create(Memo m) {
        SQLiteDatabase db = daoFactory.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DAOFactory.COLUMN_MEMO, m.getMemo());
        db.insert(DAOFactory.TABLE_MEMOS, null, values);
    }
    public void delete(Integer id) {
        SQLiteDatabase db = daoFactory.getWritableDatabase();
        db.delete(DAOFactory.TABLE_MEMOS,DAOFactory.COLUMN_ID + "=?", new String[]{String.valueOf(id)}
        );
    }

    public List<Memo> list() {
        List<Memo> list = new ArrayList<>();
        SQLiteDatabase db = daoFactory.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM memos", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(
                        cursor.getColumnIndexOrThrow("_id")
                );

                String memo = cursor.getString(
                        cursor.getColumnIndexOrThrow("memo")
                );

                list.add(new Memo(id, memo));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
}
