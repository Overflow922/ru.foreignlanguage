package ru.foreignlanguage.speak.subs;/*
 *  Copyright by Flow on 23.06.2016.
 
    Description here
 */

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class STRParser extends SubtitleParser {

    STRParser (final String fileName) {
        super(fileName);

        List<String> list = new ArrayList<>();
        try {
            list = Files.lines(new File(fileName).toPath(), StandardCharsets.UTF_8)
                        .filter((p) -> !p.trim().equals(""))
                        .collect(Collectors.toList());

        } catch(IOException e) {
            System.err.println(e.getMessage());
        }

        convert(list);
    }

    private void convert(List<String> list) {

        java.util.Iterator<String> iter = list.iterator();
        String str = "";
        SubtitleFrase frase = new SubtitleFrase();
        String t;
        while (iter.hasNext()) {
            t = iter.next();
            if (t.matches("\\d+")) {
                frase.setText(StringCorrector.correct(str));
                super.addFrase(frase);
                str = "";
                frase = new SubtitleFrase();
            } else if (t.matches("(.*?) --> (.*)")) {
                frase.setTimes(toTime(t));
            }else {
                t = t.trim();
                str += t + " ";
            }
        }
        frase.setText(StringCorrector.correct(str));
        super.addFrase(frase);
    }

    // 00:21:41,150 --> 00:22:45,11
    private void parse(String text, Subtitle out) {

        Pattern pattern = Pattern.compile("(.*?) --> (.*) (.*)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find())
        {
            out.setStartTime(convertTime(matcher.group(1)));
            out.setEndTime(convertTime(matcher.group(2)));
        }

    }

    private Time convertTime(String time) {
        Time res = null;

        Pattern pattern = Pattern.compile("(\\d+?):(\\d+):(\\d+),(\\d+)");
        Matcher matcher = pattern.matcher(time);
        if ( matcher.find()) {
            int h = Integer.parseInt(matcher.group(1));
            int m = Integer.parseInt(matcher.group(2));
            int s = Integer.parseInt(matcher.group(3));
            res = new Time(h, m, s);
        }
        return res;
    }

    private SubtitleTime toTime(String text) {
        SubtitleTime sub = null;

        Pattern pattern = Pattern.compile("(.*?) --> (.*)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find())
        {
            sub = new SubtitleFraseTime();
            sub.setStartTime(convertTime(matcher.group(1)));
            sub.setEndTime(convertTime(matcher.group(2)));
        }
        return sub;
    }
}
