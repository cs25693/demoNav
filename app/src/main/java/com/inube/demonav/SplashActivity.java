package com.inube.demonav;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.sqlcipher.database.SQLiteDatabase;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        upgradeCheck();
    }
    private void checkIsDatabaseCreated() {
        DBHelper.getInstance().createDatabase();
        if (!SharedPreferenceManager.getInstance().isDatabaseCreated(getBaseContext())) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (FileHelper.copyDatabase()) {
                        AIAApplication.encryptDatabase(SplashActivity.this,getString(R.string.database_name),"iNube1234");
                        SharedPreferenceManager.getInstance().setDatabaseCreated(getBaseContext(), true);
                        DBHelper.getInstance().resetDatabase();
                        startLoginActivity();
                    }
                }
            }).start();

        } else {
            startLoginActivity();
        }
    }


    public void upgradeCheck() {
        checkIsDatabaseCreated();
    }

    private void startLoginActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
