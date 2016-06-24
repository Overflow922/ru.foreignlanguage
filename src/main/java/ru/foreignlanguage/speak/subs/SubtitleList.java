package ru.foreignlanguage.speak.subs;

/*
 *  Copyright by Flow on 20.06.2016.
 *
 *  Basic subtitle files list interface.
 *  - Loads subtitles from:
 *      - file
 *      - filelist
 *      - video
 *  - Load frase list
 *
 *  - Search frases in subtitles
 *
 *  - Save result to file
 */

import java.util.List;

public interface SubtitleList {
    void loadSubtitlesFromFile(final String filename);
    void loadSubtitlesFromDir(final String filename);
    void loadSubtitlesFromVideo(final String filename);

    void loadFraseListFromFile(final String filename);

    List<Subtitle> search();
    void search(final String outFileName);
}
