package com.example.saikikwok.todolist.Utils;

import android.graphics.Paint;
import android.widget.TextView;

/**
 * Created by saikikwok on 9/8/16.
 */

public class TextUtils {

    public static void setTextViewStrikeThrough(TextView text, boolean strikeThrough) {
        if (strikeThrough) {
            text.setPaintFlags(text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            text.setPaintFlags(text.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }

}
