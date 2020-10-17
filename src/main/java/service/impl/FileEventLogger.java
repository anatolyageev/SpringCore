package service.impl;

import domen.Event;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import service.EventLogger;

public class FileEventLogger implements EventLogger {
    private String fileName;
    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void init(){
        this.file = new File(fileName);
        file.canWrite();
    }

    public void logEvent(Event event) throws IOException {
        FileUtils.writeStringToFile(new File(fileName),event.toString() + "\n",true);
    }
}
