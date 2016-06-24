package ru.foreignlanguage.speak.subs;
/*
 *  Copyright by Flow on 20.06.2016.
 *
 *  Contains subtitle from files.
 *   Load subtitle from file
 *
 *   Search frases
 *
 */

import java.util.List;

public interface SubtitleFile {
    void getSubtitlesFromText(final String text, final String filename);

    List<Subtitle> search(final String frase);
}
