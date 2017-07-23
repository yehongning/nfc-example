package cc.metapro.nfc;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Boollean on 2017/7/22.
 */

public class PrefHelper {

    public final String PREF_ENABLE_NFC_DIALOG = "show_enable_nfc_dialog";
    public static final String PREF_DETAILED_READ_MODE = "detailed_read_mode";
    public final String PREF_FIRST_LAUNCH = "first_launch";
    public final String PREF_CARD_TO_EMULATE = "card_to_emulate";

    public static PrefHelper sPref = null;
    private SharedPreferences mPreference;

    public PrefHelper(Context context) {
        mPreference = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static PrefHelper getInstance(Context context) {
        if (sPref == null) {
            synchronized (PrefHelper.class) {
                if (sPref == null) {
                    sPref = new PrefHelper(context);
                }
            }
        }
        return sPref;
    }

    public PrefHelper putString(String key,String value){
        SharedPreferences.Editor editor = mPreference.edit();
        editor.putString(key, value);
        editor.apply();
        return sPref;
    }

    public String getString(String key,String defaultValue){
        return mPreference.getString(key,defaultValue);
    }

    public PrefHelper putBoolean(String key,boolean Value){
        SharedPreferences.Editor editor = mPreference.edit();
        editor.putBoolean(key, Value);
        editor.apply();
        return sPref;
    }

    public boolean getBoolean(String key,boolean defaultValue){
        return mPreference.getBoolean(key,defaultValue);
    }
}