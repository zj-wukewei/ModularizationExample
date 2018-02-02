package com.wkw.ext.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by hzwukewei on 2016/5/11.
 */
public class KeyBoardUtils {

    public static void hideInputMethod(Context context, View editText) {
        if (context != null) {
            if (editText != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        }
    }

    public static void showInputMethod(Context context, View editText) {
        if (context != null && editText != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            editText.requestFocus();
            inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
        }
    }

}
