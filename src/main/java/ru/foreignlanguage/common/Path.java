package ru.foreignlanguage.common;/*
 *  Copyright by Flow on 28.06.2016.
 
    Description here
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Path {

    private java.nio.file.Path path;
    public static String getResourcePath(final String fileName, ClassLoader cl) {
        String res = "";
        try {
            res = (new File(cl.getResource(fileName).getFile())).getPath();
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
        return res;
    }

    public static String getAbsolutePath(final String filename) throws IOException, SecurityException  {
        String res;
        try {
            File f = new File(filename);
            if (f.exists() || !f.isDirectory()) {
                res = (f.getAbsolutePath());
            } else {
                throw new IOException("path (\""+filename+"\") not found.");
            }
        } catch (IOException|SecurityException e) {
            System.err.println(e);
            throw e;
        }
        return res;
    }

    public Path get(final String filepath) throws FileAlreadyExistsException {
        File f = new File(filepath);
        path = Paths.get(filepath);
        if (f.exists() || f.isDirectory()) {

            throw new FileAlreadyExistsException(filepath);
        }


        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return this;
    }

    @Override
    public String toString() {
        return path.toAbsolutePath().toString();
    }
}
