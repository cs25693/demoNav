package com.inube.demonav;

import android.content.Context;
import android.util.Log;

import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileHelper {
    public static boolean copyDatabase() {
        boolean isCopied= false;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = AIAApplication.getContext().getAssets().open(AIAApplication.getContext().getString(R.string.database_name));

            outputStream = new FileOutputStream(AIAApplication.getContext().getDatabasePath(AIAApplication.getContext().getString(R.string.database_name)));

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            isCopied = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isCopied;
    }

}
