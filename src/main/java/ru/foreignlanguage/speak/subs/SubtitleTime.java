package ru.foreignlanguage.speak.subs;

/*
 * Copyright by Flow on 20.06.2016.
 *
 * Basic subtitle time interface.
 * Contains start and end timestamps of a subtilte.
 */


public interface SubtitleTime {

    Time getStartTime();
    void setStartTime(final Time startTime);

    Time getEndTime();
    void setEndTime(final Time endTime);
}
