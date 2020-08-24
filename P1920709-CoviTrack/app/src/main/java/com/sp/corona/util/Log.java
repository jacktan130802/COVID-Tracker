

package com.sp.corona.util;

import android.content.Context;
import android.os.Environment;

import com.sp.corona.Constants;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Log {

    public static void e(String message, Object... args) {
        e(String.format(message, args));
    }

    public static void e(String message) {
        android.util.Log.e(Constants.TAG, message);
    }

    public static void i(String message, Object... args) {
        i(String.format(message, args));
    }

    public static void i(String message) {
        android.util.Log.i(Constants.TAG, message);
    }

    public static void d(String message, Object... args) {
        d(String.format(message, args));
    }

    public static void d(String message) {
        android.util.Log.d(Constants.TAG, message);
    }

    public static void w(String message, Object... args) {
        w(String.format(message, args));
    }

    public static void w(String message) {
        android.util.Log.w(Constants.TAG, message);
    }

    public static void writeToFile(Context context, Object object) {
        try {
            FileWriter out = new FileWriter(new File(context.getFilesDir(), "AuroraLogs.txt"));
            out.write(object.toString());
            out.close();
        } catch (IOException e) {
            Log.e(e.getMessage());
        }
    }

    public static void writeLogFile(Object object) {
        try {
            FileWriter out = new FileWriter(new File(Environment.getExternalStorageDirectory().getPath(), "Aurora/Logcat.txt"));
            out.write(object.toString());
            out.close();
        } catch (IOException e) {
            Log.e(e.getMessage());
        }
    }
}
