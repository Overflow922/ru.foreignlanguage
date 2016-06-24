package ru.foreignlanguage.speak.subs;/*
 *  Copyright by Flow on 23.06.2016.
 
    Description here
 */

public class SubtitleFraseTime implements SubtitleTime {

    public Time getStartTime() { return startTime; }

    public void setStartTime(final Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() { return endTime; }

    public void setEndTime(final Time endTime) {
        this.endTime = endTime;
    }

    public String toString() {
        return startTime + "-->"+ endTime;
    }
    private Time startTime;
    private Time endTime;
}
