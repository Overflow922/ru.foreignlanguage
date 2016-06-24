package ru.foreignlanguage.speak.subs;/*
 *  Copyright by Flow on 24.06.2016.
 
    Description here
 */

public class SubtitleFactory {
    private static SubtitleFactory ourInstance = new SubtitleFactory();

    public static SubtitleFactory getInstance() {
        return ourInstance;
    }

    private SubtitleFactory() {
    }

    public boolean checkString(final String str) {
        String s = str.trim();
        if (s.equals(""))
            return false;

        return true;
    }

    public Parser parse(final String filename) {
        Parser res = new STRParser(filename);

        return res;
    }
}
