package com.diptamahardhika.kanjiganbaru;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Dipta Mahardhika on 12/16/2015 .
 */
public class Message {
    public static void message(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
