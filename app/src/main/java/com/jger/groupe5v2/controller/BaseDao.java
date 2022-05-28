package com.jger.groupe5v2.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jger.groupe5v2.model.BaseEntity;
import com.jger.groupe5v2.model.Score;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public abstract  class BaseDao<T extends BaseEntity> {
    private final DataBaseHelper dbHelper;

    public BaseDao(DataBaseHelper helper){
        this.dbHelper = helper;
    }

    protected abstract String getTableName();
    protected abstract void putValues(ContentValues values, T entity);
    protected abstract T getEntity(Cursor cursor);

    /**
     * @param entity : element a ajouter dans la base
     * @return : l element créait avec son ID
     */
    public T create(T entity){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        putValues(values, entity);

        long newRowId = db.insert(getTableName(), null, values);
        entity.id = newRowId;
        return entity;
    }

    protected List<T> query(String selection, String[] selectionArgs, String sortOrder){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                getTableName(),
                null,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        List items = new ArrayList<T>();
        while(cursor.moveToNext()) {
            items.add(getEntity(cursor));

        }
        cursor.close();

        return items;
    }


    public T lastOrNull() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor =db.query(
                getTableName(),
                null,
                null,
                null,
                null,
                null,
                null);

        cursor.moveToLast();
        T last = this.getEntity(cursor);
        cursor.close();

        return last;
    }


    public long count() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select count(*) from "+getTableName(), null);
        cursor.moveToFirst();
        int count= cursor.getInt(0);
        cursor.close();

        return count;
    }

    //Insert data in table score
    public Score insertScore(String userName, Integer score) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Score scoreEntity = new Score();
        scoreEntity.setUsername(userName);
        scoreEntity.setScore(score);

        //ContentValues values = new ContentValues();
        //values.put("userName", userName);
        //values.put("score", score);

        ContentValues value = new ContentValues();
        //putValues(value, scoreEntity);

        long newRowId = db.insert(getTableName(), null, value);
        scoreEntity.id = newRowId;
        return scoreEntity;

        //long newRowId = db.insert(getTableName(), null, values);

        //closing the database connection
        //db.close();
    }

    //Get score and username from table score
    public Map<String, Integer> getScore() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT userName, score FROM scores ORDER BY score DESC", null);

        Map<String, Integer> items = new Hashtable<String, Integer>();
        while (cursor.moveToNext()) {
            String s = cursor.getString(0);
            Integer i = cursor.getInt(1);
            items.put(cursor.getString(0), cursor.getInt(1));
        }
        cursor.close();

        return items;
    }



}