package ru.foreignlanguage.speak.subs.tests;/*
 *  Copyright by Flow on 24.06.2016.
 
    Description here
 */

import static org.junit.Assert.*;

import org.junit.*;
import ru.foreignlanguage.speak.subs.Parser;
import ru.foreignlanguage.speak.subs.SubtitleFactory;

import java.util.stream.Collectors;

public class ParserFactory {
    private Parser parser;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void creation() throws Exception {
        parser = SubtitleFactory.getInstance().parse("sub-test.str");

     //   System.out.println(parser);
    }

    @Test
    public void search() throws Exception {
        parser = SubtitleFactory.getInstance().parse("sub-test.str");

        System.out.println(parser.search("")
                .stream().map(Object::toString)
                .collect(Collectors.joining(", ")));

        System.out.println(parser.search(" ")
                .stream().map(Object::toString)
                .collect(Collectors.joining(", ")));

        System.out.println(parser.search("-")
                .stream().map(Object::toString)
                .collect(Collectors.joining(", ")));
    }

}