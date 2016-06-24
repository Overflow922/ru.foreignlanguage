package ru.foreignlanguage.speak.subs;/*
 *  Copyright by Flow on 23.06.2016.
 
    Description here
 */

class Time {
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

    public String toString() {
        return hours+":"+mins+":"+seconds;
    }
}
