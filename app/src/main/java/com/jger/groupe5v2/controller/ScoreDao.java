package com.jger.groupe5v2.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jger.groupe5v2.model.BaseEntity;
import com.jger.groupe5v2.model.Score;

import java.util.Dictionary;

public class ScoreDao extends BaseDao<Score> {
    static String cleScore = "score";
    static String cleUsername="userName";
    public ScoreDao(DataBaseHelper helper) {
        super(helper);
    }

    @Override
    protected String getTableName() {
        return "scores";
    }

    @Override
    protected void putValues(ContentValues values, Score entity) {
        values.put(cleUsername,entity.getUsername());
        values.put(cleScore,entity.getScore());
    }

    @Override
    protected Score getEntity(Cursor cursor) {
        cursor.moveToFirst();
        Integer indexScore = cursor.getColumnIndex(cleScore);
        int indexUsername = cursor.getColumnIndex(cleUsername);
        Score Score = new Score();
        Score.setScore(cursor.getInt(indexScore));
        Score.setUsername(cursor.getString(indexUsername));
        cursor.close();
        return Score;
    }

}
