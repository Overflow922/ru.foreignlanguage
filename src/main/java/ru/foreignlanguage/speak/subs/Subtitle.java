package ru.foreignlanguage.speak.subs;

/*
 * Copyright by Flow on 20.06.2016.
 *
 * Basic subtitle interface. Contains frase and its timestamps
 */

public interface Subtitle {

    void setText(final String text);
    String getText();

    void setTimes(final Time from, final Time till);
    void setTimes(SubtitleTime time);
    void setStartTime(final Time from);
    void setEndTime(final Time till);

    Time getFromTime();
    Time getTillTime();
}
