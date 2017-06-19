package com.cxmax.androidjunit.sqlite;

import android.database.sqlite.SQLiteDatabase;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/3/19.
 */

public class AccountTable {

    public static final String TABLE_NAME = "account";

    public static final String ACCOUNT_ID = "id";

    public static final String ACCOUNT_EMAIL = "email";

    public static final String ACCOUNT_NAME = "name";

    public static final String ACCOUNT_PASSWORD = "password";

    public static String create(SQLiteDatabase db) {
        StringBuilder sql = new StringBuilder();
        sql.append("create table IF NOT EXISTS  ");
        sql.append(TABLE_NAME);
        sql.append("(");
        sql.append(ACCOUNT_ID).append(" text not null primary key,");
        sql.append(ACCOUNT_NAME).append(" text,");
        sql.append(ACCOUNT_EMAIL).append(" text,");
        sql.append(ACCOUNT_PASSWORD).append(" text");
        sql.append(");");
        db.execSQL(sql.toString());
        return sql.toString();
    }

    public static void drop(SQLiteDatabase db) {
        db.execSQL("drop table if exists " + TABLE_NAME);
    }

    public static void upgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
