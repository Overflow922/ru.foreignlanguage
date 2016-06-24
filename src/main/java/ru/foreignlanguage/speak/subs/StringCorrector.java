package ru.foreignlanguage.speak.subs;/*
 *  Copyright by Flow on 24.06.2016.
 
    Class erase symbols except unicode letters and apply toLower()

*/

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCorrector {

    static public String correct(final String str) {
        String res = "";

        Pattern p = Pattern.compile("[[\\w+]&&[\\D]]", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m = p.matcher(str);
        while (m.find()) {
            res += m.group();
        }

        return res;
    }
}
