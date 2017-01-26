package io.geph.android;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author j3sawyer
 */

public class AccountUtils {

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(Constants.PREFS, 0);
    }

    public static String getUsername(Context context) {
        return getSharedPreferences(context)
                .getString(Constants.SP_USERNAME, null);
    }

    public static String getPassword(Context context) {
        return getSharedPreferences(context)
                .getString(Constants.SP_PASSWORD, null);
    }

    public static boolean isSignedIn(Context context) {
        return getSharedPreferences(context)
                .getBoolean(Constants.SP_IS_SIGNED_IN, false);
    }
}
