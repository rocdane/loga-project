package com.loga.namingserver.vendor.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerWriter {
    private static LoggerWriter loggerWriter;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerWriter.class);

    private LoggerWriter(){
    }

    public static LoggerWriter getInstance(){
        if(loggerWriter ==null){
            loggerWriter = new LoggerWriter();
        }
        return loggerWriter;
    }

    public void log(String message) {
        LOGGER.info("["+new SimpleDateFormat("dd-MM-yy HH'h'mm").format(new Date())+"]"+message+"\n");
    }
}
