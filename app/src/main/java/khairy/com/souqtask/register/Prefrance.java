package khairy.com.souqtask.register;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefrance {
        public static final String PREFS_NAME = "prefs";
        static SharedPreferences.Editor prefs ;

    public static void savelang(Context context  , String language) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        prefs.remove("lang");
        prefs.putString("lang" , language);
        prefs.apply();
    }


}
