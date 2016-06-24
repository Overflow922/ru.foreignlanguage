package ru.foreignlanguage.speak.subs.tests;/*
 *  Copyright by Flow on 23.06.2016.
 
    Description here
 */

import static org.junit.Assert.*;

import org.junit.*;
import ru.foreignlanguage.speak.subs.STRParser;

import java.util.Iterator;

public class STRParserTest {
    private STRParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new STRParser("sub-test.str");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void creation() throws Exception {
        Iterator it = parser.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}