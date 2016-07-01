package ru.foreignlanguage.speak.subs;/*
 *  Copyright by Flow on 01.07.2016.
 
    Description here
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

final class LanguageList {
    final static Map<String, String> langs = new HashMap<>();

    static {
        langs.put("af", "Afrikaans");
        langs.put("an", "Aragonese");
        langs.put("ar", "Arabic");
        langs.put("ast", "Asturian");
        langs.put("be", "Belarusian");
        langs.put("br", "Breton");
        langs.put("ca", "Catalan");
        langs.put("bg", "Bulgarian");
        langs.put("bn", "Bengali");
        langs.put("cs", "Czech");
        langs.put("cy", "Welsh");
        langs.put("da", "Danish");
        langs.put("de", "German");
        langs.put("el", "Greek");
        langs.put("en", "English");
        langs.put("es", "Spanish");
        langs.put("et", "Estonian");
        langs.put("eu", "Basque");
        langs.put("fa", "Persian");
        langs.put("fi", "Finnish");
        langs.put("fr", "French");
        langs.put("ga", "Irish");
        langs.put("gl", "Galician");
        langs.put("gu", "Gujarati");
        langs.put("he", "Hebrew");
        langs.put("hi", "Hindi");
        langs.put("hr", "Croatian");
        langs.put("ht", "Haitian");
        langs.put("hu", "Hungarian");
        langs.put("id", "Indonesian");
        langs.put("is", "Icelandic");
        langs.put("it", "Italian");
        langs.put("ja", "Japanese");
        langs.put("km", "Khmer");
        langs.put("kn", "Kannada");
        langs.put("ko", "Korean");
        langs.put("lt", "Lithuanian");
        langs.put("lv", "Latvian");
        langs.put("mk", "Macedonian");
        langs.put("ml", "Malayalam");
        langs.put("mr", "Marathi");
        langs.put("ms", "Malay");
        langs.put("mt", "Maltese");
        langs.put("ne", "Nepali");
        langs.put("nl", "Dutch");
        langs.put("no", "Norwegian");
        langs.put("oc", "Occitan");
        langs.put("pa", "Punjabi");
        langs.put("pl", "Polish");
        langs.put("pt", "Portuguese");
        langs.put("ro", "Romanian");
        langs.put("ru", "Russian");
        langs.put("sk", "Slovak");
        langs.put("sl", "Slovene");
        langs.put("so", "Somali");
        langs.put("sq", "Albanian");
        langs.put("sr", "Serbian");
        langs.put("sv", "Swedish");
        langs.put("sw", "Swahili");
        langs.put("ta", "Tamil");
        langs.put("te", "Telugu");
        langs.put("th", "Thai");
        langs.put("tl", "Tagalog");
        langs.put("tr", "Turkish");
        langs.put("uk", "Ukrainian");
        langs.put("ur", "Urdu");
        langs.put("vi", "Vietnamese");
        langs.put("yi", "Yiddish");
        langs.put("zh-cn", "Simplified Chinese");
        langs.put("zh-tw", "Traditional Chinese");
    }

    static String getLanguageShort(final String lang) {
        String result = "";
        try {
            if (langs.size() > 0) {
                if (langs.containsValue(lang)) {
                    result = langs.entrySet()
                            .stream()
                            .filter(entry -> Objects.equals(entry.getValue(), lang))
                            .map(Map.Entry::getKey)
                            .limit(1)
                            .collect(Collectors.joining());
                } else {
                    throw new IOException("lang not found");
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }
}