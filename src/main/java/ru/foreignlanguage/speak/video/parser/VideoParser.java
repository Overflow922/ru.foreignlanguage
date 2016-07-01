package ru.foreignlanguage.speak.video.parser;/*
 *  Copyright by Flow on 27.06.2016.
 

 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class VideoParser implements Parser {
//    [STREAM]
//    index=0
//    codec_name=h264
//    codec_type=video
//    [/STREAM]
    public Parser loadVideoStreamInfo(final String fileName)
    {
        this.filename = fileName;
        String file = "";
        try {
            ClassLoader classLoader = getClass().getClassLoader();
//            String filepath = (new File(classLoader.getResource(fileName).getFile())).getPath();
            file = Files.lines(new File(fileName).toPath())
                    .filter((p) -> !p.trim().equals(""))
                    .collect(Collectors.joining(SEPARATOR));

        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

        Pattern pattern = Pattern.compile(OPEN_STREAM+SEPARATOR+
                                          INDEX_PATTERN+"(\\d+)"+SEPARATOR+
                                          CODEC_NAME_PATTERN+"(\\w+)"+SEPARATOR+
                                          CODEC_TYPE_PATTERN+"(\\w+)"+SEPARATOR +
                                          CLOSE_STREAM);
        Matcher matcher = pattern.matcher(file);

        while (matcher.find())
        {
            Stream stream = new Stream();
            stream.setIndex(Integer.parseInt(matcher.group(1)));
            stream.setCodecName(matcher.group(2));
            stream.setCodecType(matcher.group(3));
            streams.add(stream);
        }

        return this;
    }

    public List<Stream> getAudioStreams() {
        return null;
    }

    public List<Stream> getSubtitleStreams() {
        return streams.stream().filter((p) -> p.getCodecName().equals("subrip")).collect(Collectors.toList());
    }

    public List<Stream> getVideoStreams() {
        return null;
    }

    @Override
    public String toString() {
        return streams.stream().map(Object::toString).collect(Collectors.joining(System.lineSeparator()));
    }

    private String filename;
    private List<Stream> streams = new ArrayList<>();


    private final String OPEN_STREAM = "\\[STREAM\\]";
    private final String CLOSE_STREAM = "\\[/STREAM\\]";

    private final String INDEX_PATTERN = "index=";
    private final String CODEC_NAME_PATTERN = "codec_name=";
    private final String CODEC_TYPE_PATTERN = "codec_type=";
    private final String SEPARATOR = ",";

}
