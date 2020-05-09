package hr.ja.weboo;

import hr.ja.weboo.lib.Widget;

public class JSUtil {

    public static String getOnClick(Long id) {
        //language=js
        return
                "weboo.onClick(event, " + id + ")";
    }
}
