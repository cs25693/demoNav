package com.inube.demonav;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreferenceManager {

    private static final String SHARED_PREFERENCE_NAME = "aia_shared_preference";
    private static final String REFERENCE_CODE = "aia_shared_preference";
    private static final String PREMIUM = "aia_PREMIUM";
    private static final String FREQUENCYPREMIUM = "aia_FREQUESNCY_PREMIUM";
    private static final int MODE = Context.MODE_PRIVATE;
    private static final String IS_DATABASE_CREATED = "is_database_created";
    private static SharedPreferenceManager mInstance = new SharedPreferenceManager();
    private SharedPreferenceManager() {
        // required
    }

    public static SharedPreferenceManager getInstance(){
        return mInstance;
    }

    private SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences(SHARED_PREFERENCE_NAME, MODE);
    }

    public void setDatabaseCreated(Context context, boolean isCreated) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(IS_DATABASE_CREATED, isCreated);
        edit.apply();
    }

    public boolean isDatabaseCreated(Context context) {
        return getSharedPreferences(context).getBoolean(IS_DATABASE_CREATED, false);
    }


    public void setReferenceCode(Context context, String referenceCode) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(REFERENCE_CODE, referenceCode);
        editor.apply();
    }

    public String getReferenceCode(Context context) {
        return getSharedPreferences(context).getString(REFERENCE_CODE, null);
    }

    public void setPremium(Context context, String premium) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREMIUM, premium);
        editor.apply();
    }

    public void setFrequencyPremium(Context context, String premium) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FREQUENCYPREMIUM, premium);
        editor.apply();
    }

    public String getPremium(Context context) {
        return getSharedPreferences(context).getString(PREMIUM, null);
    }

    public String getFrequencyPremium(Context context) {
        return getSharedPreferences(context).getString(FREQUENCYPREMIUM, null);
    }
}
