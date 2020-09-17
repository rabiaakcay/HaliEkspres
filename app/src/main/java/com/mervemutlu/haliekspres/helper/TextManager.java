package com.mervemutlu.haliekspres.helper;

import android.os.Build;
import android.text.Html;

public class TextManager {
    public static String getM2(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml("m<sup>2</sup>", Html.FROM_HTML_MODE_COMPACT).toString();
        } else {
            return Html.fromHtml("m<sup>2</sup>").toString();
        }
    }
}
