package service.impl;

import domen.Event;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class CacheFileEventLogger extends FileEventLogger{
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        cache = new ArrayList<>();
    }

    public void logEvent(Event event) throws IOException {
        cache.add(event);
        if(cache.size() == cacheSize){
            writeEventsFromCache();
            cache.clear();
        }

//        FileUtils.writeStringToFile(new File(fileName),event.toString() + "\n",true);
    }

    private void writeEventsFromCache() throws IOException {
       cache.forEach(e -> {
           try {
               super.logEvent(e);
           } catch (IOException ioException) {
               ioException.printStackTrace();
           }
       });
    }

    public void destroy() throws IOException {
        if(cache.size() > 0){
            writeEventsFromCache();
        }
    }

}
