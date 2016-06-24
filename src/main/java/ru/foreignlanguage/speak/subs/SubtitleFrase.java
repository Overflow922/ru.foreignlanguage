package ru.foreignlanguage.speak.subs;
/*
 *  Copyright by Flow on 23.06.2016.

 */

public class SubtitleFrase implements Subtitle {

    public void setText(final String text){
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setTimes(final Time from, final Time till) {
        setStartTime(from);
        setEndTime(till);
    }

    public void setTimes(SubtitleTime time) {
        if (time == null)
            throw new NullPointerException("SubtitleTime cannot be null");
        this.time = (SubtitleFraseTime) time;
    }

    public void setStartTime(final Time from) {
        if (from == null)
            throw new NullPointerException("SubtitleTime cannot be null");
        time.setEndTime(from);
    }

    public void setEndTime(final Time till) {
        if (till == null)
            throw new NullPointerException("SubtitleTime cannot be null");
        time.setEndTime(till);
    }

    public Time getFromTime() {
        return time.getStartTime();
    }
    public Time getTillTime() {
        return time.getEndTime();
    }

    public String toString() {
        return time +" : "+text;
    }

private SubtitleFraseTime time;
    private String text;
}
