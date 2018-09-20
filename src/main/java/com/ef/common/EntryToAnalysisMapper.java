package com.ef.common;

import com.ef.entity.LogAnalysis;
import com.ef.entity.LogEntry;

/**
 * Created by husain on 9/19/18.
 */
public class EntryToAnalysisMapper {

    public static LogAnalysis mapLogEntry(LogEntry inLogEntry){
        LogAnalysis logAnalysis = new LogAnalysis();
        logAnalysis.setIp(inLogEntry.getIp());
        logAnalysis.setComment(inLogEntry.getLogDetails());
        return logAnalysis;
    }
}
