package ru.foreignlanguage.speak.video.parser;/*
 *  Copyright by Flow on 28.06.2016.
 
    Description here
 */

import java.nio.file.FileAlreadyExistsException;

public interface Driver {
    void setProjectPath(final String filePath, boolean isCreated) throws FileAlreadyExistsException;

    void convertVideo(String filename);
}
