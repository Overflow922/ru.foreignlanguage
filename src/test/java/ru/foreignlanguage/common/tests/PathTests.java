package ru.foreignlanguage.common.tests;/*
 *  Copyright by Flow on 28.06.2016.
 
    Description here
 */

import static org.junit.Assert.*;

import org.junit.*;
import ru.foreignlanguage.common.Path;

public class PathTests {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getResourcePath() throws Exception {
        System.out.println(Path.getResourcePath("libs/ffprobe.exe", getClass().getClassLoader()));
    }

    @Test
    public void getAbsolutePath() throws Exception {
        System.out.println(Path.getAbsolutePath("/java"));
        System.out.println(Path.getAbsolutePath(Path.getResourcePath("libs/ffprobe.exe", getClass().getClassLoader())));
    }

    @Test
    public void getPath() throws Exception {
        Path path = new Path();

        /* /java/1/2/3/4/../5 generates dir tree:
        *  /java
        *       /1
        *           /2
        *               /3
        *                   /4
        *                   /5
         */
        System.out.println(path.get("/java/1/2/3/4/../5"));
        System.out.println(path.get("/java/1/2/3/4/../5"));
    }

    @Test
    public void getToString() throws Exception {
        Path path = new Path();

 //       path = path.get("java/test1/test2/test2");
 //       System.out.println(path);
        path = path.get("");
        System.out.println(path);
    }

}