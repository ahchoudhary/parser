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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void analyze(LocalDateTime startDate, String duration, Long threshold){

        List<LogEntry> logEntries = getLogEntries(startDate, duration, threshold);

        List<LogAnalysis> analyizedList = logEntries.stream().map(EntryToAnalysisMapper::mapLogEntry).collect(Collectors.toList());
        writeToConsole(analyizedList);
        logAnalysisRepository.saveAll(analyizedList);

    }
    private List<LogEntry> getLogEntries(LocalDateTime startDate, String duration, Long threshold){
        LocalDateTime endDate = (Constants.HOURLY.equals(duration))? startDate.plusHours(1) : startDate.plusDays(1);
        List<LogEntry> dbEntries = logEntryRepository.findBlockedIp(java.sql.Timestamp.valueOf(startDate),
                java.sql.Timestamp.valueOf(endDate));
        Map<String, Long> ipCounts = new HashMap();
        for(LogEntry x: dbEntries){
            if(ipCounts.containsKey(x.getIp())){
                ipCounts.put(x.getIp(), ipCounts.get(x.getIp())+1);
            } else{
                ipCounts.put(x.getIp(), 1l);
            }
        }

        final Map<String, Long> thresholdCounts = ipCounts.entrySet().stream()
                .filter((entry)-> entry.getValue() > threshold.longValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return dbEntries.stream().filter(x -> thresholdCounts.containsKey(x.getIp())).collect(Collectors.toList());

    }

    private void writeToConsole(List<LogAnalysis>  analyizedList){
        String heading = (analyizedList.isEmpty())? "Nothing to display" : "Blocked IPs are below";
        System.out.println("\n\n "+ heading +":\n\n");
        analyizedList.stream().forEach(x -> System.out.printf("ip: %s    comments: %s\n",x.getIp(), x.getComment()));

    }
}
