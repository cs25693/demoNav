package com.inube.demonav;

import android.content.Context;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;


public class DBHelper {
    private SQLiteOpenHelper mDbOpenHelper_;

    private static DBHelper dbHelper;
    public String PASSWORD = "iNube1234";
    public static DBHelper getInstance() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }
    private DBHelper() {
        boolean databaseCreated = SharedPreferenceManager.getInstance().isDatabaseCreated(AIAApplication.getContext());
        this.mDbOpenHelper_ = new DBOpenHelper(AIAApplication.getContext(), AIAApplication.getContext().getString(R.string.database_name),
                AIAApplication.getContext().getResources().getInteger(databaseCreated ? R.integer.upgraded_database_version : R.integer.database_version));
    }
    public void resetDatabase() {
        if (mDbOpenHelper_ != null) {
            mDbOpenHelper_.close();
        }
        dbHelper = null;
        getInstance();
    }
    public void createDatabase() {
        mDbOpenHelper_.getWritableDatabase(PASSWORD);
    }
    public void readableDatabase(){
        mDbOpenHelper_.getReadableDatabase(PASSWORD);
    }
    private final static class DBOpenHelper extends SQLiteOpenHelper {

        DBOpenHelper(Context context, String name, int version) {
            super(context, name, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            /*Note : Don't put break statement after each case statement or each upgrade*/
            onCreate(db);
        }
    }
}
