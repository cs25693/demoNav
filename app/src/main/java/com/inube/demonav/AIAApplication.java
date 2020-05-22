package com.inube.demonav;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;


public class AIAApplication extends MultiDexApplication {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        SQLiteDatabase.loadLibs(this);
        context = getApplicationContext();
    }
    public static Context getAppContext() {
        return context;
    }
    public static Context getContext() {
        return context;
    }
    public static void encryptDatabase(Context ctxt, String dbName, String passphrase) {
        File originalFile = ctxt.getDatabasePath(dbName);

        try {
            if (originalFile.exists()) {
                File newFile = File.createTempFile("sqlcipherutils", "tmp", ctxt.getCacheDir());
                SQLiteDatabase db= SQLiteDatabase.openDatabase(originalFile.getAbsolutePath(),
                                "", null,
                                SQLiteDatabase.OPEN_READWRITE);
                db.rawExecSQL(String.format("ATTACH DATABASE '%s' AS encrypted KEY '%s';", newFile.getAbsolutePath(), passphrase));
                db.rawExecSQL("SELECT sqlcipher_export('encrypted')");
                db.rawExecSQL("DETACH DATABASE encrypted;");

                int version = db.getVersion();

                db.close();

                db = SQLiteDatabase.openDatabase(newFile.getAbsolutePath(),
                        passphrase, null,
                        SQLiteDatabase.OPEN_READWRITE);
                db.setVersion(version);
                db.close();

                originalFile.delete();
                newFile.renameTo(originalFile);
            }
        } catch (Exception e) {
            Log.e("exceptionDB", e.toString());
        }
    }
}
