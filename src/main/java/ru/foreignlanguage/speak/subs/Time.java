package ru.foreignlanguage.speak.subs;

/*
 *  Copyright by Flow on 23.06.2016.
 
    Description here
 */

public class Time {
    private int hours;
    private int mins;
    private int seconds;

    Time(final int h, final int mins, int sec) {
        hours = h; this.mins = mins; seconds = sec;
    }

    public int getHours() {
        return hours;
    }
    public int getMins() {
        return mins;
    }
    public int getSeconds() {
        return seconds;
    }

    public Time addHour() {
        hours++;
        return this;
    }

    public Time addMinute() {
        mins ++;
        if (mins > 60) {
            mins -= 60;
            addHour();
        }
        return this;
    }

    public Time addSecond() {
        seconds++;
        if (seconds >= 60) {
            addMinute();
            seconds -= 60;
        }
        return this;
    }

    public String toString() {
        return (hours == 0 ? "00" : hours)+":"
               +(mins == 0 ? "00" : mins)+":"
               +(seconds == 0 ? "00" : seconds);
    }
}
