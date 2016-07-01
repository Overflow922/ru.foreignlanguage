package ru.foreignlanguage.speak.subs;/*
 *  Copyright by Flow on 24.06.2016.
 
    Description here
 */

import ru.foreignlanguage.common.Path;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class SubtitleFactory {
    private static SubtitleFactory ourInstance = new SubtitleFactory();

    public static SubtitleFactory getInstance() {
        return ourInstance;
    }

    private SubtitleFactory() {
    }

    public boolean checkString(final String str) {
        String s = str.trim();
        if (s.equals(""))
            return false;

        return true;
    }

    public Parser parse(final String filePath, final String language) {
        String lng = LanguageList.getLanguageShort(language);
        Parser res = null;
        try {
            List<java.nio.file.Path> subFiles = Files.list(new File(Path.getAbsolutePath(filePath)).toPath())
                                                    .filter( (p) -> p.getFileName().toString().endsWith(".srt"))
                                                    .collect(Collectors.toList());

            System.out.println("subs count:" + subFiles.size());
            boolean isFound = false;
            for (java.nio.file.Path p: subFiles) {
                System.out.println("Parsing: " + p.toString() + " for " + language + " (" + lng + ")...");
                res = new STRParser(p.toString());
                String lang = res.getSubLanguage();
                System.out.println("lang is " + LanguageList.langs.get(lang));
                if (lang.equals(lng)) {
                    isFound = true;
                    break;
                }
            }
            if ( isFound)
                System.out.println("Selecting "+res.getFileName() +" subs.");
            else
                return null;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return res;
    }
}
