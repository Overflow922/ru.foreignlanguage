package ru.foreignlanguage.speak.subs;/*
 *  Copyright by Flow on 23.06.2016.
 
    Basic method for parser factory
 */

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public interface Parser extends Iterable<SubtitleFrase> {

    void setFileName(final String fileName);
    List<SubtitleFrase> search(String frase);
}
