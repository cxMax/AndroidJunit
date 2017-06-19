package com.cxmax.androidjunit.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cxmax.androidjunit.MyApplication;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/3/19.
 */

public class AccountDBHelper extends SQLiteOpenHelper{

    private static AccountDBHelper accountDBHelper;
    public static final int DB_VERSION = 1;
    private static final String DB_NAME = "android_junit.db";

    public AccountDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static AccountDBHelper getInstance() {
        if (accountDBHelper == null) {
            accountDBHelper = new AccountDBHelper(MyApplication.getInstance(), AccountDBHelper.DB_NAME, null, AccountDBHelper.DB_VERSION);
        }
        return accountDBHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       AccountTable.create(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
