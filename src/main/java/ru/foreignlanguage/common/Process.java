package ru.foreignlanguage.common;/*
 *  Copyright by Flow on 29.06.2016.
 
    Description here
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Process {
    private static Runtime rt;

    public Process() {
        rt = Runtime.getRuntime();
    }

    public static int execute(final String query) throws IOException, InterruptedException {
        int code;
        if (rt == null)
            rt = Runtime.getRuntime();
        try {
            java.lang.Process process = rt.exec(query);
            InputStream stderr = process.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line;
            String res="";
            while ( (line = br.readLine()) != null) {
                res += line + System.lineSeparator();
            }

            code = process.waitFor();
            if (code != 0) {
                throw new InterruptedException("Error while running process. Return code: '"+code+"'"+System.lineSeparator()
                                                +"Query = \"" + query + "\""
                                                +System.lineSeparator()+res);
            }
        } catch (IOException|InterruptedException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return code;
    }
}
