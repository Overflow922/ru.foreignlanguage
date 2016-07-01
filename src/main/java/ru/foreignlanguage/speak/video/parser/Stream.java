package ru.foreignlanguage.speak.video.parser;/*
 *  Copyright by Flow on 27.06.2016.
 
    Simple class stores information about stream in video file:
    [STREAM]
        index=1
        codec_name=ac3
        codec_type=audio
    [/STREAM]
 */

class Stream {
    public final static String AUDIO = "audio";
    public final static String VIDEO = "video";
    public final static String SUBTITLE = "subtitle";

    void setIndex(final int id) { index = id; }
    void setCodecName(final String codecName) { this.codecName = codecName; }
    void setCodecType(final String codecType) { this.codecType = codecType; }

    int getIndex() { return index; }
    String getCodecName() { return codecName; }
    public String getCodecType() { return codecType; }

    @Override
    public String toString() {
        return "index="+index+"; codec_name="+codecName+"; codec_type="+codecType;
    }

    private int index;
    private String codecName;
    private String codecType;
}
