package ru.foreignlanguage.speak.subs;/*
 *  Copyright by Flow on 23.06.2016.
 
    Basic method for parser factory
 */

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public interface Parser extends Iterable<Subtitle> {

    void setFileName(final String fileName);
    String getFileName();
    String getSubLanguage();
    List<Subtitle> search(String frase);
}
