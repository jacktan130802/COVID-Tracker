

package com.sp.corona.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.sp.corona.Constants;


public class Util {


    public static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(
                Constants.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
    }
}
