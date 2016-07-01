package ru.foreignlanguage.common.tests;/*
 *  Copyright by Flow on 29.06.2016.
 
    Description here
 */

import static org.junit.Assert.*;

import org.junit.*;
import ru.foreignlanguage.common.Process;

public class ProcessTests {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void exec() throws Exception {
        Process.execute("cmd /c C:\\java\\ru.foreignlanguage\\build\\resources\\test\\libs\\ffprobe.exe -v error -show_entries stream=index,codec_name,codec_type C:\\downloads\\Game.of.Thrones.S06E08.HDTV.1080p.Rus.Eng.DV.Kravec.mkv > C:\\english\\audio/streams.txt");

     //   Process.execute("cmd /c C:\\java\\ru.foreignlanguage\\build\\resources\\test\\libs\\ffprobe.exe -v error -show_ents stream=index,codec_name,codec_type C:\\downloads\\Game.of.Thrones.S06E08.HDTV.1080p.Rus.Eng.DV.Kravec.mkv > C:\\english\\audio/streams.txt");

    }

}