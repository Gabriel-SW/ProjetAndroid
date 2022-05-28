package com.jger.groupe5v2.controller;

import android.content.Context;

public class ScoreBaseHelper extends DataBaseHelper {

    public ScoreBaseHelper(Context context) {
        super(context, "Database", 1);
    }

    @Override
    protected String getCreationSql() {
        return "CREATE TABLE IF NOT EXISTS scores  (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "userName VARCHAR(255) NOT NULL," +
                "score INTEGER NOT NULL," +
                ")";
    }

    @Override
    protected String getDeleteSql() {
        return null;
    }



}
