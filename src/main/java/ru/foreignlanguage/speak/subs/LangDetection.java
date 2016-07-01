package ru.foreignlanguage.speak.subs;/*
 *  Copyright by Flow on 01.07.2016.
 
    Description here
 */

import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.i18n.LdLocale;
import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfile;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import com.optimaize.langdetect.text.CommonTextObjectFactories;
import com.optimaize.langdetect.text.TextObject;
import com.optimaize.langdetect.text.TextObjectFactory;

import java.io.IOException;
import java.util.List;
import com.google.common.base.Optional;
import java.util.stream.Collectors;

class LangDetection {

    private static LanguageDetector languageDetector;
    private static TextObjectFactory textObjectFactory;

    LangDetection() throws IOException {
        try {
            List<LanguageProfile> languageProfiles = new LanguageProfileReader().readAllBuiltIn();

            //build language detector:
            languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
                    .withProfiles(languageProfiles)
                    .build();

            textObjectFactory = CommonTextObjectFactories.forDetectingOnLargeText();

        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }


    String detect(List<? extends Subtitle> text) {

        TextObject textObject = textObjectFactory.forText(text.stream().map(Object::toString)
                                                            .collect(Collectors.joining()));
        Optional<LdLocale> lang = languageDetector.detect(textObject);

        return lang.get().getLanguage();
    }
}
