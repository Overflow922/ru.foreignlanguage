package ru.foreignlanguage.speak.subs;/*
 *  Copyright by Flow on 23.06.2016.
 
    Basic method for parser factory
 */

import java.util.Iterator;

public interface Parser extends Iterable<SubtitleFrase> {

    void setFileName(final String fileName);

//    boolean hasNext();
//    void next(Subtitle out);
}
