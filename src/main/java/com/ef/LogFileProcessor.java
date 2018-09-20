package com.ef;

import com.ef.common.Constants;
import com.ef.common.LogToEntityMapper;
import com.ef.common.exception.ProcessingException;
import com.ef.entity.LogEntry;
import com.ef.repository.LogAnalysisRepository;
import com.ef.repository.LogEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by husain on 9/18/18.
 */
@Component
public class LogFileProcessor {

    @Autowired
    LogEntryRepository logEntryRepository;

    @Autowired
    LogAnalysisRepository logAnalysisRepository;

    public void processFile(String fileLocation) throws ProcessingException {
        String fileContent  = getFileContent(fileLocation);
        List<LogEntry> logEntries = getLogEntries(fileContent);
        logEntryRepository.saveAll(logEntries);
    }

    public void cleanup(){
        logEntryRepository.deleteAll();
        logAnalysisRepository.deleteAll();
    }

    private String getFileContent(String fileLocation) throws ProcessingException {
        if(StringUtils.isEmpty(fileLocation)){
            throw new ProcessingException(Constants.NO_FILE_PATH_FOUND);
        }
        try{
            if(Paths.get(fileLocation) == null){
                throw new ProcessingException(Constants.WRONG_FILE_PATH_GIVEN);
            }
            String fileContent = new String(Files.readAllBytes(Paths.get(fileLocation)));
            return fileContent;
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    private List<LogEntry> getLogEntries(String fileContent){

        String[] lines = fileContent.split("\\n");
        return Arrays.stream(lines).map(LogToEntityMapper::mapLineToLogEntry).collect(Collectors.toList());
    }


}
