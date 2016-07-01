package ru.foreignlanguage.speak.video.parser;
/*
 *  Copyright by Flow on 27.06.2016.
 
    Basic video parser interface. Defines methods:
    - load information about video, audio and subtitle streams;
    - separate all subtitle, video and audio streams in separate files.

 */

import java.util.List;

interface Parser {

    //  loads information about each stream in video files
    Parser loadVideoStreamInfo(final String fileName);

    List<Stream> getAudioStreams();
    List<Stream> getSubtitleStreams();
    List<Stream> getVideoStreams();
}
