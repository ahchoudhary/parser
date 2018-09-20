package com.ef;

import com.ef.common.Constants;
import com.ef.common.EntryToAnalysisMapper;
import com.ef.entity.LogAnalysis;
import com.ef.entity.LogEntry;
import com.ef.repository.LogAnalysisRepository;
import com.ef.repository.LogEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by husain on 9/19/18.
 */
@Component
public class LogAnalyzer {

    @Autowired
    LogEntryRepository logEntryRepository;

    @Autowired
    LogAnalysisRepository logAnalysisRepository;

//take the input arguments
// query Database for that.
//update the analysis table.
// return related rows.
//print the rows in console.

    public void analyze(LocalDateTime startDate, String duration, Long threshold){

        LocalDateTime endDate = (Constants.HOURLY.equals(duration))? startDate.plusHours(1) : startDate.plusDays(1);
        List<LogEntry> logEntries = logEntryRepository.findBlockedIp(java.sql.Timestamp.valueOf(startDate),
                java.sql.Timestamp.valueOf(endDate));

        List<LogAnalysis> analyizedList = logEntries.stream().map(EntryToAnalysisMapper::mapLogEntry).collect(Collectors.toList());
        writeToConsole(analyizedList);
        logAnalysisRepository.saveAll(analyizedList);

    }
    private void writeToConsole(List<LogAnalysis>  analyizedList){
        System.out.println("\n\n blocked IPs are below:\n\n");
        analyizedList.stream().forEach(x -> System.out.printf("ip: %s    comments: %s",x.getIp(), x.getComment()));

    }
}
