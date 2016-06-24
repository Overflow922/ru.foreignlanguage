package ru.foreignlanguage.speak.subs.tests;/*
 *  Copyright by Flow on 23.06.2016.
 
    Description here
 */

import static org.junit.Assert.*;

import org.junit.*;
import ru.foreignlanguage.speak.subs.STRParser;
import ru.foreignlanguage.speak.subs.StringCorrector;

import java.util.Iterator;
import java.util.stream.Collectors;

public class STRParserTest {
    private STRParser parser;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void creation() throws Exception {
        parser = new STRParser("sub-test.str");
    }

    @Test
    public void iter() throws Exception  {
        parser = new STRParser("sub-test.str");
        Iterator it = parser.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void search() throws Exception {
        parser = new STRParser("sub-test.str");

        System.out.println(parser.search("refuse2.")
                .stream().map(Object::toString)
                .collect(Collectors.joining(", ")));
    }
}