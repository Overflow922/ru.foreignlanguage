package ru.foreignlanguage.speak.subs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**********************************************
 *  Copyright by Flow on 23.06.2016.
 *
 *  Description here
 ***********************************************/


class SubtitleParser implements Parser {

    public SubtitleParser(final String fileName) {
        setFileName(fileName);
    }

    public String getFileName() {
        return subs;
    }

    public String toString() {
        return frazes.stream().map(Object::toString).collect(Collectors.joining(System.lineSeparator()));
    }

    public void setFileName(final String fileName) {
        subs = fileName;
    }

    @Override
    public Iterator<SubtitleFrase> iterator() {
        Iterator<SubtitleFrase> it = new Iterator<SubtitleFrase>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < frazes.size() && frazes.get(currentIndex) != null;
            }

            @Override
            public SubtitleFrase next() {
                return frazes.get(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    protected Stream<SubtitleFrase> stream() {
        return frazes.stream();
    }

    public List<SubtitleFrase> search(String frase) {

        return frazes.stream().filter((p) -> p.getText().toLowerCase()
                        .contains(frase.toLowerCase()))
                       .collect(Collectors.toList());
    }

    protected void addFrase(SubtitleFrase frase) {
        frazes.add(frase);
    }

    private String subs;
    private List<SubtitleFrase> frazes = new ArrayList<>();
}